package pl.juliankominiak.beatboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import pl.juliankominiak.beatboard.model.FirebaseUserLiveData

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}
