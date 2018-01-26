#!/bin/bash

meteor build ../www --directory --server-only
cd ../www
git add --all && git commit -m "$(date +%s)" && git push

