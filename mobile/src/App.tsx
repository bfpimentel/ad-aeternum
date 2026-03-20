import {
  DMSans_400Regular,
  DMSans_500Medium,
  DMSans_700Bold,
} from "@expo-google-fonts/dm-sans";
import { Fraunces_500Medium, Fraunces_600SemiBold } from "@expo-google-fonts/fraunces";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useFonts } from "expo-font";
import { StatusBar } from "expo-status-bar";

import { BottomBar } from "@/components/BottomBar";
import { RootTabParamList, RosaryStackParamList } from "@/navigation/types";
import { HomeScreen } from "@/screens/HomeScreen";
import { LibraryScreen } from "@/screens/LibraryScreen";
import { ReaderScreen } from "@/screens/ReaderScreen";
import { SettingsScreen } from "@/screens/SettingsScreen";
import { RosarySessionProvider } from "@/state/RosarySessionContext";
import { colors } from "@/theme/tokens";

const Tab = createBottomTabNavigator<RootTabParamList>();
const RosaryStack = createNativeStackNavigator<RosaryStackParamList>();

function RosaryStackNavigator() {
  return (
    <RosaryStack.Navigator screenOptions={{ headerShown: false }}>
      <RosaryStack.Screen name="Library" component={LibraryScreen} />
      <RosaryStack.Screen name="Reader" component={ReaderScreen} />
    </RosaryStack.Navigator>
  );
}

export default function App() {
  const [fontsLoaded] = useFonts({
    DMSans_400Regular,
    DMSans_500Medium,
    DMSans_700Bold,
    Fraunces_500Medium,
    Fraunces_600SemiBold,
  });

  if (!fontsLoaded) {
    return null;
  }

  return (
    <RosarySessionProvider>
      <NavigationContainer>
        <StatusBar style="dark" backgroundColor={colors.background} />
        <Tab.Navigator
          initialRouteName="Home"
          tabBar={(props) => <BottomBar {...props} />}
          screenOptions={{ headerShown: false }}
        >
          <Tab.Screen name="Home" component={HomeScreen} />
          <Tab.Screen name="Rosary" component={RosaryStackNavigator} />
          <Tab.Screen name="Settings" component={SettingsScreen} />
        </Tab.Navigator>
      </NavigationContainer>
    </RosarySessionProvider>
  );
}
