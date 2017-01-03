## Structure
We separate our project into many library modules by its own functionality.

- `resources`: contain app's resources:
  - styles
  - colors
  - dimension
  - drawables
- `common`: contain anything which can be shared between modules. e.g. utilities, widgets. etc.
- `data`: handle data flow. This module contains 
  - entity
  - model
  - api
  - database
- `app`: application module which connects other modules
- `feature`: each features or group of features should be put in a separate module that we call `feature`. e.g. **product**, **home**.

## Build & Run
### Gradle
Gradle is supported by default.
~~~shell
./run
~~~

### Buck
Buck is fast build system which developed by facebook.

#### Install OkBuck
Follow this [instruction](https://github.com/uber/okbuck#installation) to install

~~~shell
# step 1
./gradle :buckWrapper

# step 2
./gradle :okbuck
~~~

#### Build
~~~shell
./buckw build appDebug
~~~

#### Install & run
~~~shell
./buckw install --run appDebug
~~~


No specific

## License

    Copyright 2016 Tiki Corp

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

