# Pocket Overflow

## Story

You got fed up with not having your own personalized access to your favourite Q&A/quote collection/joke stream site that is always in your pocket. The time has finally come to connect to the Internet with your own app. Use a public API provided by an online service, and list the latest contents of your interest.

You can find a link in the background materials section with a list of a few public APIs that you can use. Feel free to choose any source that you find interesting and build your app around that topic. Examples: 

- the newest Android posts from StackOverflow
- Joke of the day and list of jokes,
- Programmer quote of the day and list of programmer quotes, 
- Current weather in Budapest and weather forecast for each day of the current week.

## What are you going to learn?

- how to download a `JSON` file from the internet
- usage of `OkHttp` library
- usage of `RxJava` library
- usage of `Retrofit` library
- practice the usage of MVP

## Tasks


1. Display one piece of data from a public API service of your choice

    - The app has an `Activity` where a single data item is downloaded from the public API and is displayed
    - While the app is loading, a gray overlay and a progressbar is displayed covering the screen

2. Display a list of data from the same public API service using another endpoint

    - The app has an `Activity` where a list of data from the public API is downloaded and displayed using `RecyclerView`
    - While the app is loading data, a gray overlay and a progressbar is displayed on top of the list.
    - When the list of items is empty, a text is displayed on the screen to inform the user instead of the empty `RecyclerView`

3. The app needs to have a custom icon and handle device rotation.

    - The application has an icon other than the default
    - The application handles device rotation


## General requirements


 - The package name is `com.codecool.pocketoverflow`, and the name of the application is `Pocket Overflow`
 - The project is using the MVP architecture pattern
 - All colors, dimens and string resources in the project are extracted into their respective `xml` files.
 - The project uses the `Retrofit` library to load `JSON` from the APIs
 - The project uses the `RxJava` library to handle the API responses asynchronously
 - The project uses the `OkHttp` library as an HTTP client

## Hints

- [This](https://jsonformatter.org/) is a useful website to format `JSON` files into a more readable version.
- Even though there are a lot of types available in `RxJava` you will not need more than `Single` in this task,
  because we expect the APIs to send only one response to each request.

## Starting repository

Follow [this link](https://journey.code.cool/v2/project/solo/blueprint/pocket-overflow/java) to create your project repository.

## Background materials

- :exclamation: [Simple OkHttp example video](https://www.youtube.com/watch?v=oGWJ8xD2W6k)
- :open_book: [Christina Lee: Intro to RxJava video](https://www.youtube.com/watch?v=XLH2v9deew0)
- :exclamation: [Exploring RxJava 2 for Android video](https://jakewharton.com/exploring-rxjava-2-for-android/)
- :exclamation: [Retrofit + RxJava github example](https://github.com/farizdotid/sample-retrofit2-rxjava-android)
- :open_book: [Simple HTTP with Retrofit 2 video](https://jakewharton.com/simple-http-with-retrofit-2/)
- [Collection of public APIs](https://github.com/public-apis/public-apis)
