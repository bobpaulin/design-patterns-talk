#Scene 2

Narrator: It has been quite a busy day for our GreenHorn developer. He has been opening and closing Jira Tickets thorough the day. Let's see what he's up to.

JR: Ok, last commit... and let's create a PR... Let me notify him. He usually check these pretty quickly

GB: (sound in the distance)... "What in the name of Merlin's Beard is this code!". 
    Walks next to JR. "Explain this ball of wax that you just submitted"

JR: My last PR? Oh, it's our new configuration system! And it was a doozy, we had like 4 different requests for enhancements on it. 

GB: Ok, how about this... Walk me through the history of your code... sometimes we can learn much more by looking at each commit than at the train wreck that is your PR.

JR: Ah, sure... It all started simply enough. We needed a Config Holder. This config holder would read a properties file from the filesystem and then allows you to get a config from that. (Shows ConfigHolder.java). 

GB: Ok... that... looks actually OK. 

JR: See then Our Devops guy came in and open a Jira Request on it... He wanted the ability to support JSON... Apparently that's the only thing they can write configuration into... He was mumbling a lot about kubernetes, and saying "REDRUM" a lot... he migh've been thirsty (show redrum image from The Shining).

So I went ahead and added support. I started by adding a boolean to know if it was a Json File, and depending on that, we read the configuration with the appropiate loader. (Describes code)

I was proud of myself. That "IF" statement was the highlight of my career. Perfect in every way.

GB: grunt... I think I see what's going on, but go on... this was probably not the "Last" request that came in

JR: Of course not...So then came Bob the Consultant, and he asked for it to be "Dynamically reloaded", so that when he modifies a value in the file, the next time a property is being requested, it will reload the new settings. (Shows the code for ConfigHolderReloader)

See... I was proud of this one too... I was going to copy and paste the reload code in the constructor and in the reload method, but then thought... oh, code reuse, and I can just call reload() from the constructor. Pretty neat eh?

GB: Jesus, believe me, code reuse is the least of your problem.

JR: (getting excited), but then came our architect, and say that since we need to work on different environments, we might have a "base" file, and then possibly extra files for each individual environment, and that we should have the ability to use THREE files 

GB: Hold on... why three

JR: I don't know... that's what he said, so I did that...

GB: You know, that it IS not illegal in this country to ask "why"...

JR: "Why?"... Never mind, going back to my code (opens ConfigHolderReloaderMultiple)... Look! I added it. I admit it might be a better way to make it neater, but this actually works! I added the files in the constructor, and then I just keep adding and loading to the same map. 

Is cool because now we have hierarchies! The first file is the "base" and then you can change the value of only a particular setting by putting it on the second or third file.

GB: It burns... 

JR: What burns?

GB: My eyes... with the fury of a thousand suns... 

JR: well, in that case, before you go blind...

our QA opened the last ticket, she wanted to be able to override the values by simply passing a map of the settings. That way she can create tests that have specific configs without having to change any files. So what I did is added the Map at the end of the constructor, and voila!

We now have the ability to override everything by anything! (explains ConfigHolderReloadMultipleOverrides)

GB: Oh Jr... do you like what you see?

JR: What do you mean, it works right? 

GB: I guess in the technicality of the term, it does work... but, is it maintainable?

JR: But I thought we were doing "Ship it and delete it" development Strategy

GB: No, no, that was THAT other startup (leave the people to wonder which startup)

See... the issue that you have in this code is that is a matter of many accidents. You, my Greenhorn, have been boiled...

JR: Boiled?

GB: So there's the story of the Frog in the pot of water. If you drop the frog into a pot of hot boiling water, it'll jump out

But if you drop it in warm water and slowly boil it, the frog will stay there as the incremental difference in temperature didn't register with it. 

The same thing happened here. Each request, on its own was a small thing to add to your codebase. But eventually you added so much cruft that this is falling over.

JR: What do you mean falling over...

GB: Say for example... I want to support YAML... I heard that's what we are replacing JSON with.  (Shows more pictures of redrum with a devops montage)
  
  Where would you hypotetically... add the code

JR: Hm... I would need to add it in the If statement, and then add a boolean, and then copy and paste all these code again but with yaml... 

GB: What happens if I send both boolean values of Yaml, and Json as true...?

JR: Well, nobody will EVER do that, since everyone uses the API as it was intended! (both of us look at the audience, breaking the 4th wall)

GB: It's error prone... This code if fraught with peril. If I don't send the right number of files, or the right combination of values, it breaks. And we got here by the small increments. 

But one thing you need to start picking up is that you have permission to "refactor". And that means, that you can take extra time to actually rearrange things so that they are not "messy". It's an important skillset, and the best developers will refactor constantly. 

JR: But if I refactor I'm not closing things as fast as I can

GB: Well, what you are actually doing is "saving you time" in the future. Because I saw a ticket coming for the YAML support. When you refactor code, you make it easy on you to keep modifying your code. The 10 minutes you "saved" by not refactoring turns into hours or days... or even months of finding bugs, or maintainability issues. So you should ALWAYS take those 10, 30, or 60 minutes to refactor something.

So in that sense, how would we refactor this ball of wax (to be honest I'm surprised that you didn't use a GoTo statement)

JR: I tried! but I didn't find a GoTo statement in Java

GB: (hehehe, there is one, but I will never tell you about it, why give you scissors when you are always tripping up)

JR: Ok...hm... I mean... I'm not sure... I guess, I could create more methods for the if / else, and loading it.

GB: That's small potatoes, and unimportant... The big question you need to ask, is what is your class doing?

JR: Oh, this, loads properties

GB: ... (waits)

JR: From Property files... and Json

GB: ... (waits more)

JR: um... it can also load from multiple files...

GB: ... and...?

JR: Override them?

GB: So you essentially destroyed the Single Responsibility Principle. While is true that your class "loads" properties, it does it in so many ways that is hard to separate each concern... and that's why you have problems adding a new one, because everything is intertwined. Luckily, there is another Design Pattern for that.

JR: Oh, from the Go4?

GR: Correct, it's called the decorator pattern. It allows to enhance a class without having to modify it, or extend it... hence, it's called the "Decorator", because we decorate (like a xmas tree) what the class can do.


So we start simple. First, we create an interface to indicate what is the concern of this provider. It need to be easy, clean, and simple. No shenanigans on features. (shows ConfigProvider)

JR: But hey! How can then you do all the If statements, and switching and file loading. If the interface is that simple, it can't possibly do all what we need to right?

GB: You're jumping a little ahead. Patience Greenhorn. Let's create the "Original" ticket and show the Property Config Provider
(shows PropertyConfigProvider). Simple enough right? 

JR: Right. No beautiful if statements, nothing. This is like going backwards

GB: Hold on, remember, we are going to refactor all this...

Let's now create a new implementation for Json files. (Walks through the JsonConfigProvider)

Pretty simple right? No need to pass if statements. You instantiate the one you need, and use that... working with properties, you use the propertyconfigprovider, you want the json you can use the jsonpropertyprovider.

JR: But, but... ok, I guess... But what happens with the request to load on demand?

GB: Notice that we "load" all the properties any time that we request each config... We, in fact have created the "reload always" condition by default. But you're right... sometimes we might not want to do that...

So we create the opposite, which is "Caching"... so we now have a CachedConfigProvider (shows code). The code is merely 22 lines of code, and it does the caching. If the value is not found, it "calls" the delegate... 

And this is the crux of the Decorator Pattern. 

JR: Why are you taking in a ConfigProvider as part of this ConfigProvider? It looks like one of those russian nesting dolls.

GB: See, that's the purpose of the Decorator... it essentially says "Give me one of your providers", if I need to enhance it, I will, and if I don't I simply call and get the response from the decorated pattern.

Take a look on how we do the GetConfig... We have a map of cached values, and if we don't find it, we "use the delegate" to find out what value we should return... In the process, we populate the cache and then is done. 

JR: I guess so, but isn't that more complicated?

GB: There is more classes for sure, but for example... Say that you want to change, or add a "time to live" to the cache. 

Do I need to modify the property configuration provider?

JR: ah, eeh..yss...? maybe...No

GB: What about the JsonProvider?

JR: Hm... no

GB: Correct, if I have a caching "concern" it will only be done in the CachedConfigProvider. I Isolated all the changes for caching to that provider.

Same if I need to upgrade how we read Json... If we want to switch from GSon to Jackson, or vice-versa I only need to change the JsonConfigProvider

JR: So ... you write more code at the beginning, but it helps isolate concerns? Is that it? 

GB: Mostly yes, we make it easier for others to come and extend our base. For example, how would you do Yaml.

JR: Oh, you just open the JSON and add a boolean

GB: Stares sternly at JR....

JR:... or maybe...hm..., Oh! I would implement a new ConfigProvider!

GB: You're starting to get it. Yes, the right answer for adding a new type of file format is to implement a new config provider.

So how would we add the capability of multiple configs?

JR: Ah ah... I would add the String in the PropertyProvide...oh, wait... I'm doing the same as I was doing before... hm... 

If I think about it... this is new functionality... maybe I would want to add that functionality outside of any of the classes.

GB: Bingo! You are decorating. So let's walk through how would that look like...(shows multiconfigprovider)

JR: Wow, the code is simple on each little part, and actually have more features! I can do two or ten configurations.

GB: Right, once you break code this way, things taht may have looked impossible or hard to do become easy.

JR: Ok... let me try the last one... We need to add Overrides... So I'll create OverridableConfigPOrovider (walks through it)

Oh wow... this is simple. The code on each one is so simple that I feel I'm not a real developer anymore.

GB: On the contrary. A "real" developer doesn't get excited with hard-to-understand code. This was an anti-pattern of the 60s, and lord do I know about it... Now real developers are those that can write code on which bugs cannot hide. 

If you do something silly here on any of the classes, I'm able to catch it pretty quickly, same as you. Since each concern is isolated to each decorator, we are not distracted by having too many things in our head at a time. 

That's the biggest benefit of the decorator, and why is one of my favorite patterns. 

JR: I got it... but is hard to think about. 

GB: It takes some practice, but a telltalle sign is if you have "boolean" or "enum" flags. If you are using too many of these, then it might be a sign that you are adding too much logic into your class.

JR: Ok Greybeard, this was great!

GB: Let's see that new Pull request now.












