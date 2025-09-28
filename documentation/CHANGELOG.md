# Changelog

All notable changes to SandeshVahak will be documented in this file.

## [0.1] - 2025-01-XX

### Added
- Initial release of SandeshVahak
- Secure SMS message synchronization via Telegram Bot API
- Background SMS monitoring and syncing service
- Material Design 3 user interface
- Automatic message sync without third-party services
- Privacy-first design with end-to-end encryption
- Cross-device message access through Telegram
- Battery-optimized background processing
- Boot completion receiver for automatic service restart
- Database backup and export functionality
- Permission management for SMS access
- Onboarding screens for new users

### Technical Features
- Kotlin-based Android application
- Jetpack Compose UI framework
- Room database for local message storage
- WorkManager for background tasks
- Content observer for SMS changes
- Foreground service for reliable operation
- Direct boot awareness for early startup

### Security & Privacy
- Uses only official Telegram Bot API
- No external servers or third-party services
- All data remains within user's Telegram account
- End-to-end encryption through Telegram
- Local database with secure backup options

### Permissions Required
- READ_SMS: To access and sync SMS messages
- INTERNET: To communicate with Telegram Bot API
- POST_NOTIFICATIONS: To show sync status
- FOREGROUND_SERVICE: For reliable background operation
- WAKE_LOCK: To ensure sync completion
- RECEIVE_BOOT_COMPLETED: To restart service after reboot

---

## Previous Project History

This project was originally cloned from a photo management app called "Whitehole" but has been completely rewritten to provide secure SMS message synchronization functionality. The new app maintains no connection to the original photo management features and focuses entirely on secure message syncing through Telegram.




