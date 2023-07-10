package tj.tcell.tcellexchange.utils

data class UiState<T>(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: T? = null,
    val unknownError: String = ""
) {
    companion object {
        val Empty = UiState
    }
}

