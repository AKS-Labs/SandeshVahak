<!-- ---------- Header ---------- -->
<div align="center">
  <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp" width="120" height="120">
  <h1>SandeshVahak</h1>
  <h4>💬 <em>"SandeshVahak" (संदेश वाहक) = "Message Carrier" in Sanskrit</em></h4>
  <h3>Syncs Your Messages Before They Fade</h3>
  <p><strong>Sync SMS from your secondary device to your primary device via Telegram - stay connected everywhere!</strong></p>
  <p>🔒 <em>100% Private • Zero Tracking • Your Messages, Your Rules</em></p>

<!-- ---------- Badges ---------- -->
  <div align="center">
    <img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square">
    <img alt="Downloads" src="https://img.shields.io/github/downloads/AKS-Labs/SandeshVahak/total?color=c3e7ff&style=flat-square">
    <img alt="Last commit" src="https://img.shields.io/github/last-commit/AKS-Labs/SandeshVahak?color=c3e7ff&style=flat-square">
    <img alt="Repo size" src="https://img.shields.io/github/repo-size/AKS-Labs/SandeshVahak?color=c3e7ff&style=flat-square">
    <img alt="Stars" src="https://img.shields.io/github/stars/AKS-Labs/SandeshVahak?color=c3e7ff&style=flat-square">
    <br>
</div>

<!-- ----------   Labels ---------- -->
<div align="center">
  <img alt="API" src="https://img.shields.io/badge/Api%2029+-50f270?logo=android&logoColor=black&style=for-the-badge"/>
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/>
  <img alt="Jetpack Compose" src="https://img.shields.io/static/v1?style=for-the-badge&message=Jetpack+Compose&color=4285F4&logo=Jetpack+Compose&logoColor=FFFFFF&label="/>
  <img alt="material" src="https://custom-icon-badges.demolab.com/badge/material%20you-lightblue?style=for-the-badge&logocolor=333&logo=material-you"/>
</div>
</div>

---

## 🌟 **What is SandeshVahak?**

**Never miss a message again!** SandeshVahak is a clever SMS sync app that bridges your secondary and primary devices using Telegram as a secure relay, ensuring you stay connected no matter which device you're using.

### 💡 **How It Works**
1. **🤖 Create a Telegram Bot** (2 minutes of magic ✨)
2. **📱 Connect SandeshVahak** to your bot (one-time setup)
3. **☁️ Enjoy Seamless SMS Sync** - Your messages flow between devices like clockwork! ⏰

---

## 🔐 **Privacy & Security First**

### ❌ **What We DON'T Do**
- 🚫 No sneaky telemetry or tracking
- 🚫 No creepy analytics or data mining
- 🚫 No shady third-party services
- 🚫 No annoying ads or monetization schemes

### ✅ **What We DO**
- **🎯 Direct device ↔ Telegram communication only**
- **👑 You own and control your bot (you're the boss!)**
- **🔍 Fully open source and auditable (peek under the hood!)**
- **🛡️ Complete data privacy guaranteed (your secrets stay secret)**

### 🛡️ **Independent Security Audit Results**
*Comprehensive code review and security analysis confirms our privacy claims:*

| **Security Assessment** | **Audit Findings** |
|-------------------------|---------------------|
| **EXCELLENT PRIVACY IMPLEMENTATION** | • Only Telegram API: All network traffic goes exclusively to Telegram Bot API<br>• No Third-Party Services: Zero analytics, telemetry, or tracking endpoints<br>• No Hidden Endpoints: Comprehensive code review found no undisclosed network calls |
| **VERIFIED CLEAN DEPENDENCIES** | • No Analytics Libraries: No Firebase, Google Analytics, Crashlytics, or similar<br>• Minimal Dependencies: Only essential libraries (Compose, Room, Retrofit, Telegram Bot)<br>• No Tracking SDKs: Explicitly excludes LeakCanary and other potential tracking tools |
| **STRONG ENCRYPTION IMPLEMENTATION** | • AES-256 Encryption: Bot tokens and chat IDs stored with AndroidX Security Crypto<br>• Proper Key Management: Uses Android Keystore with AES256_GCM encryption |
| **APPROPRIATE PERMISSION USAGE** | • READ_SMS: Used exclusively for reading SMS messages as stated<br>• INTERNET: Only for Telegram API communication<br>• FOREGROUND_SERVICE: For background SMS monitoring<br>• No Excessive Permissions: No location, camera, contacts, or other sensitive permissions |
| **CLEAN DEPENDENCY PROFILE** | • Reputable Libraries: All dependencies from trusted sources (Google, Square, JetBrains)<br>• No Suspicious Libraries: No unknown or potentially malicious dependencies<br>• Open Source: Telegram bot library is open source and auditable |
| **TRANSPARENT BACKGROUND OPERATIONS** | • SMS Monitoring: Only monitors SMS changes for sync purposes<br>• No Unauthorized Operations: All background tasks clearly related to SMS sync functionality<br>• Proper Service Implementation: Uses foreground services with user-visible notifications |
| **NO HIDDEN FUNCTIONALITY** | • ProGuard Rules: Standard obfuscation for release builds, no suspicious exclusions<br>• Open Source: All functionality is visible and auditable |

**Security Verdict: TRUSTWORTHY** - *Technical audit confirms genuine privacy protection*

---

## ✨ **Key Features**

- 💬 **Smart SMS Sync** - Bridge your devices via Telegram magic
- 🔒 **100% Private** - Zero snooping, zero tracking, zero drama
- 🎨 **Beautiful Material Design 3** - Eye candy that actually works
- ⚡ **Lightning-Fast Sync** - Your messages appear faster than you can blink
- 🧠 **Intelligent Sync** - Only syncs what's new (no duplicate chaos)
- 🌍 **Cross-Device Freedom** - Access your messages from anywhere, anytime
- 🛠️ **Telegram Bot Power** - Rock-solid reliability with enterprise-grade security
---

## 📥 **Get SandeshVahak**

<div align="center">
  <a href="https://github.com/AKS-Labs/SandeshVahak/releases">
    <img alt="Get it on GitHub" src="https://user-images.githubusercontent.com/69304392/148696068-0cfea65d-b18f-4685-82b5-329a330b1c0d.png" height="80px">
  </a>
  <br>
  <p><strong>🆓 Free Forever • 🔓 Open Source • 🚀 Ready to Rock</strong></p>
</div>

---

## 🚀 **Quick Setup**

### 🤖 **Step 1: Create Your Telegram Bot**
1. Open Telegram → Search `@BotFather` (the bot creator bot!)
2. Send `/newbot` → Follow the friendly prompts
3. Copy your shiny new bot token 🔑
4. Create private group → Add your bot → Type `/start` → Grab that chat ID

### 📱 **Step 2: Connect & Conquer**
1. Download SandeshVahak from GitHub (it's free! 🎁)
2. Enter your bot token and chat ID (copy-paste magic)
3. Watch the SMS sync magic happen! 🎉✨

---

## 📱 **Screenshots**

*Beautiful screenshots coming soon! 📸 (We're busy making the app even more awesome)*

---

## 🤝 **Community**

- 🐛 **Found a bug?** [Tell us here!](https://github.com/AKS-Labs/SandeshVahak/issues)
- 💡 **Got a cool idea?** [Share it with us!](https://github.com/AKS-Labs/SandeshVahak/issues)
- 👨‍💻 **Want to contribute?** Fork it, code it, submit it!
- ⭐ **Love SandeshVahak?** Star the repo and spread the word!

---

## 🙏 **Credits**

## Credits
* [Whitehole Project](https://github.com/beradeep/whitehole/) for the base code.
* [kotlin-telegram-bot](https://github.com/kotlin-telegram-bot) for the Telegram bot library.
* [Material Icons](https://material.io/resources/icons/) for the icons.

---

<div align="center">
  <h3>🔒 **Your Messages. Your Privacy. Your Control.** 🔒</h3>
  <p><strong>Experience seamless SMS sync between devices without compromising your privacy!</strong></p>

  <a href="https://github.com/AKS-Labs/SandeshVahak/releases">
    <strong>📥 Download SandeshVahak Today!</strong>
  </a>
</div>
