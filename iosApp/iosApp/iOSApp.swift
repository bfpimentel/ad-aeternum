import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        AdAeternumComponentKt.startDI()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
