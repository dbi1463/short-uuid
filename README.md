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

To decode the shortId back to normal UUID

```kotlin
val uuid = ShortUUID.from("1jVCAW2GFeBa4mazB-O9BR")
println(uuid.toString()) // 53e6693a-0aaa-4e94-a116-2a397fc89975
```

or specify the characters to decode

```kotlin
val characters = "0123456789ABCDEFGHJKLMNPRSTUVWXY"
val uuid = ShortUUID.from("7USX6A185P9T68L1FGPSX75V12", characters)
println(uuid.toString()) // fbcf8ca0-a0b7-4e8c-8a05-f0be7c72f022
```

See [here](https://medium.com/%E9%96%92%E8%AB%87%E8%BB%9F%E9%AB%94%E6%9E%B6%E6%A7%8B/%E9%96%92%E8%AB%87%E8%BB%9F%E9%AB%94%E6%9E%B6%E6%A7%8B-%E5%86%8D%E8%81%8A-uuid-fee7b1a3a12e?source=collection_home---5------1-----------------------) for the details of the implementation.

# Named UUID

This project also contains a utility to generate UUID v5 from name and namespace that follows some (not all) requirements
specified in the RFC 4122

```kotlin
val name = "abc"
val namespace = "@123.xyz"
val uuid = NamedUUID.from(name, namespace)
println(uuid.toString()) // cda47728-893f-5f74-b7ce-7bb1773ed34f
```

See [here](https://medium.com/%E9%96%92%E8%AB%87%E8%BB%9F%E9%AB%94%E6%9E%B6%E6%A7%8B/%E9%96%92%E8%AB%87%E8%BB%9F%E9%AB%94%E6%9E%B6%E6%A7%8B-uuid-%E4%B9%8B%E4%B8%89%E9%83%A8%E6%9B%B2-a68c01fff9b5?source=collection_home---5------0-----------------------) for the details of the implementation. 