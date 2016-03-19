[![Build Status](https://travis-ci.org/team-supercharge/rxsnappy.svg?branch=master)](https://travis-ci.org/team-supercharge/rxsnappy)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RxSnappy-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2864)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.supercharge/rxsnappy/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.supercharge/rxsnappy)
#RxSnappy

RxSnappy is a thread safe rxjava wrapper for the great [SnappyDB](http://www.snappydb.com/) fast key-value database for Android.

This library offers a time based raw response cache solution on client side.


## Usage
* __Get artifact__

```gradle
compile 'io.supercharge:rxsnappy:0.3.0'
```



* __In application's onCreate() call__

```java
RxSnappy.init(context);
```

* __Create RxSnappyClient__

```java
RxSnappyClient rxSnappyClient = new RxSnappyClient();

rxSnappyClient.setObject(key, object).subscribe(object -> ...);

rxSnappyClient.getObject(key, Object.class).subscribe(object -> ...);

```
* __Every record automatically gets an id which id is the date in milliseconds of the insertion__

```java

public Observable<FooResponse> getFooResponse(String token, BarRequest barRequest){

//Generate unique key 
final String key = RxSnappyUtils.generateKey("fooresponse", token, barRequest);

//Look for in cache with a time interval (ms)
//If the data in cache is older than 15 sec it throws an exception
return rxSnappyClient.getObject(key, 15000L, FooResponse.class)
	.onErrorResumeNext(retrofitApi.getFooResponse(token, barRequest)
		.flatMap(fooResponse -> rxSnappyClient.setObject(key, fooResponse));
}

```

* [See tests for examples
](./rxsnappy/src/androidTest/java/io/supercharge/rxsnappy/WorkingWithRetrofitTest.java)


## Contributing

Please fork this repository and create a pull request
Any contributions, large or small, major features, bug fixes, unit tests are welcomed and appreciated but will be thoroughly reviewed and discussed.

Run the instumentation tests before PR!

License
--------
RxSnappy is opensource, contribution and feedback are welcomed

[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

    Copyright 2015 Supercharge

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
## Author 

[richardradics](https://github.com/richardradics)   

[![Supercharge](http://s23.postimg.org/gbpv7dwjr/unnamed.png)](http://supercharge.io/)

