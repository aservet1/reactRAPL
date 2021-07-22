# webRAPL
Monitor a remote server's energy consumption via web service.

![image](https://user-images.githubusercontent.com/54599694/126587277-bc42eb0a-2525-4b19-9e2b-28672a9b2001.png)

## How this works
This is still pretty basic at the moment. For now, all you need to do is run the server (from the `webRAPL/backend` folder,
`./runserver.sh`) and run the frontend (from `webRAPL/frontent`, `npm start`). Then click around the web app.

You can also use the backend on its own. It tells you which port it's running on once you start it up. I've pinged it from another computer on
my local network with `curl` and a very simple Python script. You can use it as a standalone web service, not just through the frontend I wrote.

## Security disclaimer!
`./runserver.sh` requires root access to your system to get the energy readings, so vet this code and take security precautions as you need.

I know it's bad practice to run a web server as root, I'm looking into either separating the root-required part as a different process that isn't
directly accessed by the web server, or making a user with specific permissions to access the energy devices (and nothing else) and running the
server from there.

The project is still very much in progress, so I haven't made this safe for anyone to run without vetting their concerns. Feel free to contact me with questions.

## About the Java Energy API: jRAPL
[jRAPL](https://github.com/aservet1/jRAPL) is an Energy reading library in Java. jRAPL = Java RAPL. RAPL is Running Average Power Limit,
an interface that Intel processors have to monitor energy consumption and also do stuff like set power limits. This project uses RAPL
utilities to read and report energy consumption, implemented in a Java library.

You need sudo access to run any jRAPL operations. So when starting up any programs that have jRAPL readings, make sure to run as root. The interface must
also be manually triggered, do `sudo modprobe msr` and it will stay open until your computer shuts down. If you get any energy reading errors reported,
it's likely because you haven't done one of these things.

See [jRAPL](https://github.com/aservet1/jRAPL) README for general information about jRAPL, although some implementation details might differ. That is the 
development repository for the University research project I'm developing it for.

## Contact
Alejandro Servetto {aservet1@binghamton.edu}
