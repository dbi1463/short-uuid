# Short UUID

This is a demo project to show how to encode UUID into a shorter string. For example, to encode UUID with characters `0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-`

```kotlin
val uuid = fromString("53e6693a-0aaa-4e94-a116-2a397fc89975")
print(uuid.shortId()) // 1jVCAW2GFeBa4mazB-O9BR
```

or encode UUID with user-friendly characters `0123456789ABCDEFGHJKLMNPRSTUVWXY`

```kotlin
val characters = "0123456789ABCDEFGHJKLMNPRSTUVWXY"
val uuid = fromString("fbcf8ca0-a0b7-4e8c-8a05-f0be7c72f022")
print(uuid.shortId(characters)) // 7USX6A185P9T68L1FGPSX75V12
```

Right now, it does not support 32-bits unicode characters, therefore, you can not encode UUID with emoji :cry:
