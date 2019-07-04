# Android MVVM Architecture + Clean Architecture concepts: Sample App

This repository contains a sample Android app that implements the MVVM architecture and also some concepts of Clean Architecture.

![](mvvm.gif)

The project is structured in the following way:

**app**: it contains the view classes along with their corresponding view model and also the DI package that contains all the dependencies needed by the features.<br/> 
**data**: it contains all the data accessing (it implements the interfaces defined on the domain module)<br/>
**domain**: it contains all the bussiness classes

## Libraries

- RxJava2
- Dagger2
- Lottie
- Picasso 
- Navigation (jetpack)
