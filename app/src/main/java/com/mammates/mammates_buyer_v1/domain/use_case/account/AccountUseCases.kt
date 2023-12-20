package com.mammates.mammates_buyer_v1.domain.use_case.account

data class AccountUseCases(
    val getAccountUseCase: GetAccountUseCase,
    val updateAccountUseCase: UpdateAccountUseCase,
    val updateProfilePictureUseCase: UpdateProfilePictureUseCase,
    val changePasswordUseCase: ChangePasswordUseCase
)