# react-rapl
Using a version of jRAPL to access CPU energy levels, then allows a user to look at them via a React app.

Might allow the user to interact (RAPL powercap or DVFS) with the energy interface once I actually 
have that incorporated into jRAPL. But for now it's just energy monitoring and stuff in terms of what it currently can already do.

The Energy-Related files for reactRAPL are based off of the jRAPL files in the master branch of https://github.com/aservet1/jRAPL as of December 13th 2020 17:00 EST

This is a totally clean copy in the sense that reactRAPL and jRAPL are now completely separate entities. No links
between each other. While they started with the same files, there is no future association between these files.
Any creations / update / deletions or literally any other operation on one file in reactRAPL will have zero impact
on any of the files in jRAPL, and vice versa.

So prety much i copied jRAPL's files to star tthis project, but these are distinct projects now. Totally different
branches of the originally same project. I started this project off by deleting a whole bunch of the previously jRAPL
files because they were totally irrelevant to the scope of this project and it's very likely I'll fundamentally change
a lot of the files that are left as I go forward with this project. There's probably going to be stuff left in common
between the projects but my main point is that I'm not guaranteeing any future association between these projects and
any similarity between these files will just be happen stance, and not tied to a special link between the reactRAPL and
jRAPL files changes to one would ever affect the other.


See https://github.com/aservet1/jRAPL README for general information about jRAPL, although some implementation details will
likely differ for this version.

If you're a total stranger and you're looking through this, don't rely on any of the documentation. I might have updated it,
but honestly the best you can do to find out what the code does is read though all the code and just take the documentation
with a grain of salt. I haven't gone through this to make sure someone could go through it and not get confused yet...not that
the code is so bad that you'll lose your mind trying to read it or anything, but just know that this project is still under
development and getting changed all the time, so the documentation / comments throughout my code may or may not be in sync with
what the code itself actually does.
