package ru.lionzxy.printbox.di.app

import dagger.Component
import ru.lionzxy.printbox.di.auth.AuthComponent
import ru.lionzxy.printbox.di.auth.AuthModule
import ru.lionzxy.printbox.di.files.FilesComponent
import ru.lionzxy.printbox.di.files.FilesModule
import ru.lionzxy.printbox.di.print.PrintComponent
import ru.lionzxy.printbox.di.print.PrintModule
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun plus(module: AuthModule): AuthComponent
    fun plus(module: PrintModule): PrintComponent
    fun plus(module: FilesModule): FilesComponent
}