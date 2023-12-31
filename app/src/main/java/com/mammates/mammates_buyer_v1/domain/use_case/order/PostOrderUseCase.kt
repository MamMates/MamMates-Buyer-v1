package com.mammates.mammates_buyer_v1.domain.use_case.order

import com.mammates.mammates_buyer_v1.common.Resource
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.OrderBuyerItem
import com.mammates.mammates_buyer_v1.domain.repository.OrderRepository
import com.mammates.mammates_buyer_v1.util.ErrorMessage
import com.mammates.mammates_buyer_v1.util.HttpError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    operator fun invoke(
        token: String,
        food: List<OrderBuyerItem>,
        sellerId: Int
    ): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val message = orderRepository.postOrder(token, food, sellerId).message
            emit(Resource.Success(message))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()
            errorMessage?.let {
                try {
                    val jsonObject = JSONObject(it.charStream().readText())
                    when (jsonObject.getInt("code")) {
                        400 -> {
                            emit(Resource.Error(
                                jsonObject.getJSONObject("data")
                                    .getString("error")
                                    .takeIf { it.isNotEmpty() }
                                    ?: HttpError.BAD_REQUEST.message
                            ))
                        }

                        401 -> {
                            emit(Resource.Error(HttpError.UNAUTHORIZED.message))
                        }

                        403 -> {
                            emit(Resource.Error(HttpError.FORBIDDEN.message))
                        }

                        404 -> {
                            emit(Resource.Error(HttpError.NOT_FOUND.message))
                        }

                        409 -> {
                            emit(Resource.Error(HttpError.CONFLICT.message))
                        }

                        500 -> {
                            emit(Resource.Error(HttpError.INTERNAL_SERVER_ERROR.message))
                        }

                        else -> {
                            emit(Resource.Error(ErrorMessage.UNEXPECTED.message))
                        }
                    }
                } catch (e: JSONException) {
                    emit(Resource.Error(ErrorMessage.JSON_PARSE.message))
                }
            }
        } catch (e: IOException) {
            emit(Resource.Error(ErrorMessage.NO_INTERNET.message))
        }
    }
}