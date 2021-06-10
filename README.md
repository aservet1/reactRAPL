# httpRAPL
Monitor a remote server's energy consumption through a web app.

## jRAPL
Energy reading library in Java. jRAPL = Java RAPL. RAPL is Running Average Power Limit, an interface that Intel processors have
to monitor energy consumption and also do stuff like set power limits. For this project, RAPL will be done to read and report 
energy consumption. So jRAPL for the purposes of this project is an Energy Reading Library in Java.

You need sudo access to run any jRAPL operations. So when starting up any programs that have jRAPL readings, you have to do `sudo java DriverProgram...`.
If you forget to do that, you'll get read errors when you try to read the energy registers.

See [jRAPL](https://github.com/aservet1/jRAPL) README for general information about jRAPL, although some implementation details might differ. That is the 
development repository for the University research project I'm developing it for.

## How this works
This is pretty basic, since it's a free-time side project. For now, all you need to do is run the server (from the `httpRAPL/backend` folder,
`./runserver.sh`) and run the frontend (from `httpRAPL/frontent`, `npm start`). Then click around the web app.

You can also use the backend on its own. It tells you which port it's running on. I've pinged it from another computer on my local network with `curl`
and a very simple Python script. You can use it as a standalone web service, not just through the frontend I wrote.

## Security disclaimer!
`./runserver.sh` requires root access to your system to get the energy readings, so vet this code and take security precautions as you need.

I know it's bad practice to run a web server as root, I'm looking into either separating the root-required part as a different process that isn't
directly accessed by the web server, or making a user with specific permissions to access the energy devices (and nothing else) and running the
server from there.

The project is still very much in progress, so I haven't made this safe for anyone to run without vetting their concerns. Feel free to contact me with questions.

## Contact
Alejandro Servetto {aservet1@binghamton.edu}
