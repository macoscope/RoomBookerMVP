#Room Booker Project
##Project structure
###MVP gradle module
Java module with no Android dependencies. It contains following packages:

- `.mvp` - a package that contains all classes directly related to Model-View-Presenter architecture. 
	- `.mvp.model` - Model classes
	- `.mvp.presenter` - Presenters. They responds to UI actions,  uses usecases to operate modify model objects and control View elements depending on use case results. It subscribes to observable returned by usecase `execute()` method.
	- `.mvp.view` - View interfaces that should be implemented by Android Activities, Fragments, or any other UI component.
- `.domain` - contains use cases that are used to operate on Model objects. Each use case implements `Usecase` interface that contains `execute()` that returns rxJava `Observable` object. 
- `.repository` - contains `Repository` interface used by use cases to operate on model. Implementation for this interface is provided by Android module using Retrofit.

###App gradle module
Android gradle module.

- `.activity` - Android activities, they can be treated as MVP Views by implementing corresponding `View` interfaces from `mvp` gradle module.
- `.fragment` - Android fragments. Like activities, they can be treated as Views.
- `.adapter` - adapters for RecyclerViews and ViewPagers
- `.gson` - classes for `gson` JSON parser used to parse network responses.
- `.rest` - implementation of mvp `Repository` interface with Retrofit 2 library.
- `.injector` - Dagger 2 related classes
	- `.scope` - Two custom scopes are defined: `PerApplication` and `PerActivity`.
	- `.module` - `ApplicationModule` and `NetworkModule` are `PerApplication` defined modules. `NetworkModule` provides implementation for `Repository` interface. The rest of modules have `PerActivity` scope. `EventModule` and `CalendarsModule` provides injections for presenters and use cases defined in `mvp` gradle module.
	- `.component` - contains `dagger` components. `ApplicationComponent` provides `PerApplication` scoped dependencies. Rest of components provide `PerActivity` scoped dependencies.

##Launching stub backend service
1. Install `ruby` on your machine
2. Install [Sinatra](http://www.sinatrarb.com/) framework gem using `sudo gem install sinatra`
3. Launch backend service from `backend` folder `ruby sinatra.rb`

##Limitations of mocked API
Responses from sinatra framework service are based on static JSON files which are located in `\backend` folder. There is no implementation for storing data. 
POST request operation for events have mocked response with static event in body - it has no influence on events list. Also POST request has no error handling for operation, so adding events operation is always successful. Sinatra service implementation is added only to show the general structure of API interface. 


##Used libraries

- [Dagger2](https://github.com/google/dagger)
- [Retrofit2 (beta2)](https://github.com/square/retrofit)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Butterknife](https://github.com/JakeWharton/butterknife)

##Copyright

Published under the [MIT License](LICENSE).
Copyright (c) 2015 [Macoscope][] sp. z o.o.

  [Macoscope]: http://macoscope.com