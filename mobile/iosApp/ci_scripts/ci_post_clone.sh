#!/bin/sh

#  ci_post_clone.sh
#  iosApp
#
#  Created by Bruno Pimentel on 16/11/23.
#  Copyright © 2023 pro.aeternum. All rights reserved.
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 17.0.8-zulu
