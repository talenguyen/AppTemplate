# Structure
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

# Build & Run
## Gradle
Gradle is supported by default.
~~~shell
./run
~~~

## Buck
Buck is fast build system which developed by facebook.

### Install OkBuck
Follow this [instruction](https://github.com/uber/okbuck#installation) to install

~~~shell
# step 1
./gradle :buckWrapper

# step 2
./gradle :okbuck
~~~

### Build
~~~shell
./buckw build appDebug
~~~

### Install & run
~~~shell
./buckw install --run appDebug
~~~