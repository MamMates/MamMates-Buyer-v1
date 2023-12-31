package com.mammates.mammates_buyer_v1.di

import android.content.Context
import com.mammates.mammates_buyer_v1.common.Constants
import com.mammates.mammates_buyer_v1.data.repository.AccountRepositoryImpl
import com.mammates.mammates_buyer_v1.data.repository.AuthRepositoryImpl
import com.mammates.mammates_buyer_v1.data.repository.FoodRepositoryImpl
import com.mammates.mammates_buyer_v1.data.repository.IntroRepositoryImpl
import com.mammates.mammates_buyer_v1.data.repository.OrderRepositoryImpl
import com.mammates.mammates_buyer_v1.data.repository.TokenRepositoryImpl
import com.mammates.mammates_buyer_v1.data.source.local.IntroPreference
import com.mammates.mammates_buyer_v1.data.source.local.TokenPreference
import com.mammates.mammates_buyer_v1.data.source.remote.MamMatesApi
import com.mammates.mammates_buyer_v1.domain.repository.AccountRepository
import com.mammates.mammates_buyer_v1.domain.repository.AuthRepository
import com.mammates.mammates_buyer_v1.domain.repository.FoodRepository
import com.mammates.mammates_buyer_v1.domain.repository.IntroRepository
import com.mammates.mammates_buyer_v1.domain.repository.OrderRepository
import com.mammates.mammates_buyer_v1.domain.repository.TokenRepository
import com.mammates.mammates_buyer_v1.domain.use_case.account.AccountUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.account.ChangePasswordUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.account.GetAccountUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.account.UpdateAccountUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.account.UpdateProfilePictureUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.auth.AuthLoginUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.auth.AuthRegisterUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.auth.AuthUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.food.FoodUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.food.GetFoodRecommendationUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.food.GetSearchFoodUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.food.GetStoreFoodUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.intro.GetIntroIsDoneUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.intro.IntroUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.intro.SetIntroIsDoneUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.order.GetOrderDetailUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.order.GetOrdersUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.order.OrderUseCases
import com.mammates.mammates_buyer_v1.domain.use_case.order.PostOrderUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.token.ClearTokenUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.token.GetTokenUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.token.SetTokenUseCase
import com.mammates.mammates_buyer_v1.domain.use_case.token.TokenUseCases
import com.mammates.mammates_buyer_v1.util.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMamMatesApi(): MamMatesApi {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeader = req.newBuilder()
                .build()
            chain.proceed(requestHeader)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MamMatesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesIntroPreference(@ApplicationContext context: Context): IntroPreference {
        return IntroPreference(context.dataStore)
    }

    @Provides
    @Singleton
    fun providesTokenPreference(@ApplicationContext context: Context): TokenPreference {
        return TokenPreference(context.dataStore)
    }

    @Provides
    @Singleton
    fun providesIntroRepository(introPreference: IntroPreference): IntroRepository {
        return IntroRepositoryImpl(introPreference)
    }

    @Provides
    @Singleton
    fun providesTokenRepository(tokenPreference: TokenPreference): TokenRepository {
        return TokenRepositoryImpl(tokenPreference)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(mamMatesApi: MamMatesApi): AuthRepository {
        return AuthRepositoryImpl(mamMatesApi)
    }

    @Provides
    @Singleton
    fun providesFoodRepository(mamMatesApi: MamMatesApi): FoodRepository {
        return FoodRepositoryImpl(mamMatesApi)
    }

    @Provides
    @Singleton
    fun providesOrderRepository(mamMatesApi: MamMatesApi): OrderRepository {
        return OrderRepositoryImpl(mamMatesApi)
    }

    @Provides
    @Singleton
    fun providesAccountRepository(mamMatesApi: MamMatesApi): AccountRepository {
        return AccountRepositoryImpl(mamMatesApi)
    }

    @Provides
    @Singleton
    fun providesIntroUseCase(introRepository: IntroRepository): IntroUseCases {
        return IntroUseCases(
            getIntroIsDoneUseCase = GetIntroIsDoneUseCase(introRepository),
            setIntroIsDoneUseCase = SetIntroIsDoneUseCase(introRepository)
        )
    }

    @Provides
    @Singleton
    fun providesTokenUseCases(tokenRepository: TokenRepository): TokenUseCases {
        return TokenUseCases(
            getTokenUseCase = GetTokenUseCase(tokenRepository),
            setTokenUseCase = SetTokenUseCase(tokenRepository),
            clearTokenUseCase = ClearTokenUseCase(tokenRepository)
        )
    }

    @Provides
    @Singleton
    fun providesAuthUseCases(authRepository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            authLoginUseCase = AuthLoginUseCase(authRepository),
            authRegisterUseCase = AuthRegisterUseCase(authRepository)
        )
    }

    @Provides
    @Singleton
    fun providesFoodUseCases(foodRepository: FoodRepository): FoodUseCases {
        return FoodUseCases(
            getSearchFoodUseCase = GetSearchFoodUseCase(foodRepository),
            getStoreFoodUseCase = GetStoreFoodUseCase(foodRepository),
            getFoodRecommendationUseCase = GetFoodRecommendationUseCase(foodRepository)
        )
    }

    @Provides
    @Singleton
    fun providesOrderUseCases(orderRepository: OrderRepository): OrderUseCases {
        return OrderUseCases(
            getOrdersUseCase = GetOrdersUseCase(orderRepository),
            getOrderDetailUseCase = GetOrderDetailUseCase(orderRepository),
            postOrderUseCase = PostOrderUseCase(orderRepository)
        )
    }

    @Provides
    @Singleton
    fun providesAccountUseCase(accountRepository: AccountRepository): AccountUseCases {
        return AccountUseCases(
            getAccountUseCase = GetAccountUseCase(accountRepository),
            updateAccountUseCase = UpdateAccountUseCase(accountRepository),
            updateProfilePictureUseCase = UpdateProfilePictureUseCase(accountRepository),
            changePasswordUseCase = ChangePasswordUseCase(accountRepository)
        )
    }
}