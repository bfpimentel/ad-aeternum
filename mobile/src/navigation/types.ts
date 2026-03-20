import { NavigatorScreenParams } from "@react-navigation/native";

export type RosaryStackParamList = {
  Library: undefined;
  Reader: undefined;
};

export type RootTabParamList = {
  Home: undefined;
  Rosary: NavigatorScreenParams<RosaryStackParamList>;
  Settings: undefined;
};
