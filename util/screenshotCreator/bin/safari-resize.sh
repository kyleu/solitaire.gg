#!/usr/bin/env bash
osascript -e "tell app \"Safari\" to set the bounds of window 1 to {"$1", "$2", "$3", "$4"}";
