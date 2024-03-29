# libMCdev

a library to speed up development across all my mods by:
1. symplifying unessecary re-usable code.
2. being a portable drop-in API.
3. provide additional helpers with tedious features.

## What will this be used for?

Currently, this is being used as a quick hacky replacement for all my mods' logging solutions, but will likely grow into a tool over time.

## Planned Features
- [X] An efficient and effective logging library.
- [ ] **Easy** client synced persistent server variables.
- [X] The ability to fetch OS-specific details, such as Linux Distributions. (CentOS, Rocky, and ~~Alpine Linux~~ **_probably_ won't work,** as they _usually_ don't use `/etc/os-release`)
- [X] A simple video and animated image rendering API. (_sorta_ works for video)
- [ ] A modern UI system that allows you to throw things together fast.
- [ ] An animation and camera utilites system for semi-scripted events.
- [ ] A mesh utilities/generation system. (more on this later)
  
  <hr>
  
  ### **_possibly happening_**
- [ ] Paper version?
- [ ] Folia version?
- [ ] Splitting the mod into multiple modules.

This library is under Apache 2.0, feel free to Jar-in-Jar this mod. 