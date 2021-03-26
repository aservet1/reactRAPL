# httpRAPL
Monitor a remote server's energy consumption through a web app.

### jRAPL
Energy reading library in Java. jRAPL = Java RAPL. RAPL is Running Average Power Limit, an interface that Intel processors have
to monitor energy consumption and also do stuff like set power limits. For this project, RAPL will be done to read and report 
energy consumption. So jRAPL for the purposes of this project is an Energy Reading Library in Java.

You need sudo access to run any jRAPL operations. So when starting up any programs that have jRAPL readings, you have to do `sudo java DriverProgram...`.
If you forget to do that, you'll get read errors when you try to read the energy registers.

See https://github.com/aservet1/jRAPL README for general information about jRAPL, although some implementation details will differ. That's my main "actually working on the jRAPL library and testing it and adding features" project.
For this project, I just copied over my jRAPL files, deleted a lot of things that weren't immediately useful for this application, and
modified them from there to fit this project.

### How this works
Suuuuuper basic right now still a baby project. For now, all you need to do is run the server (from the `httpRAPL/backend` folder, `./runserver.sh`) and run the frontend (from `httpRAPL/frontent`, `npm start`). Then click around the web app.

### Documentation out of date
I wouldn't trust the documentation, I can't promise that I updated all of it at this point. All code in `master` is stable and works
as intended, but I don't have any instrucitons or reliable documentation. Like I said, I just copied over files from my other jRAPL project
and am in the process of modifying them to fit this program.
