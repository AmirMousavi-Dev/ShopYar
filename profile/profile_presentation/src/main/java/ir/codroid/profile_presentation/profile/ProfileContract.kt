package ir.codroid.profile_presentation.profile

import ir.codroid.core.domain.model.ShopInfo

class ProfileContract {

    data class State(
         val shopInfo: ShopInfo = ShopInfo()
    )


}