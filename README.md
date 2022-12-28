## Custom Annotation
-----

### Way 1
Using `@AutoService` from Google auto service.
Branch `master`

Remember:
- Be sure to enable `annotation processing` in your IDE (presumably Jetbrain)

### Way 2
Although using `@AutoService` is too much convenient, we try to live in standard Java land.

In this way, we try to use maven to help us build and configure our project.


Go ahead, check out branch `noauto`
```
git checkout noauto
```

To see it run, just find `Main` class in `annotation-user` module

How it works

- We separate our pet project into two modules: 
  + `annotation-processor` module: define our custom annotation (i.e `BuilderProperty`) and annotation processor (i.e `BuilderProcessor` which generate the `Builder` class (`PersonBuilder`))
  + `annotation-user` module: define a class (`Person`) which user our custom annotation `BuilderProperty`; and use it  
- Both modules have same parent (which is `me.thucdx.custom-annotation:1.1-SNAPSHOT`) 
- In pom of `annotation-user` module, we use `maven-compiler-plugin` with configuration about the classpath containing our processor and specify processor to run.
 
See the excerpt from `annotation-user` pom.xml below and read the comment IN UPPERCASE for more details 
```
 <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    
                    // TELL MAVEN COMPILER PLUGIN ABOUT CLASSPATH OF OUR PROCESSOR
                    // THIS WILL EXTREMELY IMPORTANT IF THE PROCESSOR IS IN ANOTHER MODULE OF SAME PROJECT (THAT IS NOT PRE-BUILT and NOT IN ANY OF MAVEN REPOSITORY)
                    // SEE MORE: https://maven.apache.org/plugins/maven-compiler-plugin/compile-mojo.html#annotationProcessorPaths
                    <annotationProcessorPaths>
                        <path>
                            <groupId>${project.parent.groupId}</groupId>
                            <artifactId>annotation-processor</artifactId>
                            <version>${project.version}</version>
                        </path>
                    </annotationProcessorPaths>
                    
                    // SPECIFY ANOTATION PROCESSOR TO RUN
                    // SEE MORE: https://maven.apache.org/plugins/maven-compiler-plugin/compile-mojo.html#annotationProcessors
                    <annotationProcessors>
                        <annotationProcessor>
                            me.thucdx.BuilderProcessor
                        </annotationProcessor>
                    </annotationProcessors>
                    
                    // SPECIFY LOCATION OF GENERATED SOURCES.
                    // TIPS: IF YOUR INTELLIJ STILL PLACE RED UNDERLINE UNDER YOUR REFERENCE TO GENERATED CLASS, 
                    // TRY TO "RIGHT CLICK -> MAVEN -> RELOAD PROJECT"
                    <generatedSourcesDirectory>${project.build.directory}
                        /generated-sources/
                    </generatedSourcesDirectory>
                </configuration>
            </plugin>
        </plugins> 
```
- To compile the project, from the parent folder `custom-annotation`, type
```
mvn clean compile
```
By doing this, the module `annotation-processor` will be first compiled in just a normal way.
After that, `annotation-user` module will be compiled by the same `maven-compiler-plugin` as in compiling `annotation-processor` module.
However, by supplying classpath for annotation processor, we've taught this plugin the place to find and which annotation processor to run (in this case, generated new class file) before compiling.

