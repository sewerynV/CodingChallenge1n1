package com.seweryn.piotr.domain.usecase

interface SuspendUseCase<PARAMS, RESULT> {
  suspend operator fun invoke(params: PARAMS): Result<RESULT>
}