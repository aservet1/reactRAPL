# react-rapl
Using a React.js frontend to request jRAPL readings. Then you can monitor a server's energy through the internet.

### jRAPL
Energy reading library in Java. jRAPL = Java RAPL. RAPL is Running Average Power Limit, and interface that Intel processors have
with their energy consumption. Use RAPL to interact with the energy registers. For this project, RAPL will be done to read and report 
energy consumption. So jRAPL for this project is an Energy Reading Library in Java.

You need sudo access to run any jRAPL operations. So when starting up any programs that have jRAPL readings, you have to do `sudo java DriverProgram...`.
If you forget to do that, you'll get read errors when you try to read the energy registers.

See https://github.com/aservet1/jRAPL README for general information about jRAPL, although some implementation details will
likely differ for this version. That's my main "actually working on the jRAPL library and testing it and adding features" project.
For this project, I just copied over my jRAPL files, deleted a lot of things that weren't immediately useful for this application, and
modified them from there to fit this project.

### How this works
I'll get into that once I have a cohesive product on my master branch. Still under development.

### Documentation out of date
I wouldn't trust the documentation, I can't promise that I updated all of it at this point. All code in `master` is stable and works
as intended, but I don't have any instrucitons or reliable documentation.
