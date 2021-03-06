package ru.lionzxy.printbox.view.print.ui

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.lionzxy.printbox.data.model.PrintCartModel
import ru.lionzxy.printbox.data.model.User

@StateStrategyType(AddToEndSingleStrategy::class)
interface IPrintView : MvpView {
    fun setCartModel(cartModel: PrintCartModel)
}
