{
  description = "AD AETERNUM Expo mobile development shell";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    {
      self,
      nixpkgs,
      flake-utils,
    }:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = import nixpkgs {
          inherit system;
          config.allowUnfree = true;
        };
      in
      {
        devShells.default = pkgs.mkShellNoCC {
          packages = with pkgs; [
            bun
            nodejs_22
            watchman
            jq
            cocoapods
            oxfmt
          ];

          shellHook = ''
            export EXPO_NO_TELEMETRY=1
            export CI=0

            if [ -d "/Applications/Xcode.app/Contents/Developer" ]; then
              export DEVELOPER_DIR="/Applications/Xcode.app/Contents/Developer"
            fi

            echo "AD AETERNUM dev shell ready (bun + expo)"
          '';
        };
      }
    );
}
