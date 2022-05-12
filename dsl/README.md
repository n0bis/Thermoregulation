# Run web editor
Make sure the MWE2-Workflow have ran
Right click the ```org.healthydrone.dsl-expressions.web``` run as ```Java Application``` search for ```ServerLauncher - org.healthydrone.dsl.exoressions.web``` click ```ok```. Might need to restart Eclipse, if you can't find it

# Getting the libraries over to frontend
```wget --mirror --reject=index.html,style.css -nH -P public/ localhost:8080/{xtext,xtext-resources,webjars}```

# Rules
```
Rule temperature <namespace>
minTemperature <int> action LEDblink <red> | <green> | <blue>
maxTemperature <int> action LEDblink <red> | <green> | <blue>
end
```
