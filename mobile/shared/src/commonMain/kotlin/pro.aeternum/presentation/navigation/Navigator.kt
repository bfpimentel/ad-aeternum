package pro.aeternum.presentation.navigation

internal interface Navigator {
    fun navigate(destination: Destination)
    fun navigateBack()
}
