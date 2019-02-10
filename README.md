# CurrencyExchange app for Android
Android currency exchange application made with MVP, Retrofit2, Dagger2, ReactiveX, Room

## Project description
* This project was created used MVP architecture which provides loose coupling making the app more robust, testable and maintainable
* Dagger2 is used for dependancy injection
* ReactiveX is used for accessing local Room database and while making Retrofit2 requests
* Layouts were made to be functional, not pretty (I'm not very experienced with UI/UX)

## Project structure
### data
* this folder contains folder named db which contains Room database, entities and DAOs. 
* It also contais a folder named network which contains EVPApiService and EVPApiResponse
### di (dependancy injection)
* this folder contains AppModule, which has methods for providing app wide dependancies and other Dagger2 files
### ui
* this folder contains activity files (including base folder with BaseView, BasePresenter, BaseInteractor, ect)
* Main folder contains MainActivity, MainPresenter, MainInteractor which do the majority of work in this app
* Each MVP component has it's own interface whose methods are being used to comunicate between MVP components
### utils
* AppConstants - self-explainatory: it contains all of application constant values
* CommonUtils - common methods that are used in the app
* ScheduleProvider - a helper class for ReactiveX
### root
* this contains only CurrencyExchangeApp
