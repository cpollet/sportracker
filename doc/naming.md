# Packages names #
All classes should in subpackages of `net.cpollet.sportracker`

Use 

* `data` holds the data objects
	* `builder` holds builders for data objects
* `quantities` holds the quantities classes
* `units` holds the unit classes
* `service` holds services
* `spring` holds spring-related classes
* `web`
	* `controller` holds the web controllers
	* `data` holds the data that is exchanges via the RESTful API
* `cucumber.steps` holds the cucumber steps definition classes

# Classes and Interfaces #
* Don't prefix or suffix interfaces names;
* Don't use prefixes or suffixes. When no better name, you can use `Default-` as a prefix;
* Prefer the prefix `Base-` over the prefix `Abstract-` for abstract classes;
* Builder names are suffixed with `-Builder`;
* Test classes are named after the class/interface implementations they are testing, with the `Test-` prefix;
* Cucumber steps are suffixed with `-Steps`.

[http://stackoverflow.com/a/2814831](http://stackoverflow.com/a/2814831),
[http://stackoverflow.com/a/2815440](http://stackoverflow.com/a/2815440)

# Spring #
* Bean Names/IDs are prefixed with `st.`;
* Avoid using `@Autowired`.
