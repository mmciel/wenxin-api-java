## 特性

- 支持流式输出
- 支持同步输出
- 支持对话式，支持问答式，接口统一。
- 已支持ERNIE-Bot和ERNIE-Bot-turbo
- 官方文档：https://cloud.baidu.com/doc/WENXINWORKSHOP/s/flfmc9do2



## 如何使用

> 可以脱离Spring Boot使用,直接创建Client对象即可

```java
    @Test
    void contextLoads1() {
        // 问答
        List<MessageItem> messages = new ArrayList<>();
        messages.add(MessageItem.builder().role(MessageItem.Role.USER).content("帮我用Python实现计算斐波那契数列").build());
        ChatResponse chat = wenXinClient.chat(messages, ModelE.ERNIE_Bot);
        System.out.println(JSONUtil.toJsonStr(chat));
		/*
		{"id":"as-hfgfbitirj","object":"chat.completion","created":1691127828,"isTruncated":false,"result":"当然可以！以下是一个简单的Python函数，用于计算斐波那契数列的前n项：\n\n\n```python\ndef fibonacci(n):\n    if n <= 0:\n        return []\n    elif n == 1:\n        return [0]\n    else:\n        fib = [0, 1]\n        for i in range(2, n):\n            fib.append(fib[i-1] + fib[i-2])\n        return fib\n```\n这个函数接受一个正整数n作为输入，并返回一个包含前n个斐波那契数的列表。如果n为0或1，函数将返回相应的特殊情况。否则，它将通过迭代计算并返回前n个斐波那契数。\n\n你可以通过调用这个函数并传入你想要计算的斐波那契数列的项数来计算斐波那契数列。例如，要计算前10个斐波那契数，可以这样调用函数：\n\n\n```python\nfibonacci(10)\n```\n这将返回一个包含前10个斐波那契数的列表，如下所示：\n\n\n```python\n[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]\n```\n希望这可以帮助你实现斐波那契数列的计算！","needClearHistory":false,"usage":{"promptTokens":14,"completionTokens":277,"totalTokens":291}}
		 */
    }

    @Test
    void contextLoads2() throws InterruptedException {
        // 流式问答
        List<MessageItem> messages = new ArrayList<>();
        messages.add(MessageItem.builder().role(MessageItem.Role.USER).content("帮我用Python实现计算斐波那契数列").build());
        wenXinClient.streamChat(messages, new ConsoleExampleEventSourceListener(), ModelE.ERNIE_Bot);
		/*
		2023-08-04 13:46:08.063  INFO 9832 --- ...: 建立sse连接...
		2023-08-04 13:46:08.078  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127967,"sentence_id":0,"is_end":false,"is_truncated":false,"result":"当然可以！","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":4,"total_tokens":18}}
		2023-08-04 13:46:09.257  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127968,"sentence_id":1,"is_end":false,"is_truncated":false,"result":"以下是一个简单的Python函数，用于计算斐波那契数列的前n项：\n\n\n```python\ndef fibonacci(n):\n    if n \u003c= 0:\n        return []\n    elif n == 1:\n        return [0]\n    ","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":45,"total_tokens":63}}
		2023-08-04 13:46:10.453  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127969,"sentence_id":2,"is_end":false,"is_truncated":false,"result":"else:\n        fib = [0, 1]\n        for i in range(2, n):\n            fib.append(fib[i-1] + fib[i-2])\n        return fib\n```\n这个函数接受一个正整数n","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":42,"total_tokens":105}}
。。。
		2023-08-04 13:46:15.451  INFO 9832 --- ...: 返回数据结束了
		2023-08-04 13:46:15.451  INFO 9832 --- ...: 关闭sse连接...
		*/
    }

    @Test
    void contextLoads3() {
        // 对话
        List<MessageItem> messages = new ArrayList<>();
        messages.add(MessageItem.builder().role(MessageItem.Role.USER).content("帮我用Python实现计算斐波那契数列").build());
        messages.add(MessageItem.builder().role(MessageItem.Role.ASSISTANT).content("好的，这是通过循环方式实现的函数：...").build());
        messages.add(MessageItem.builder().role(MessageItem.Role.USER).content("你写的不好，请用递归方式实现").build());
        ChatResponse chat = wenXinClient.chat(messages, ModelE.ERNIE_Bot);
        System.out.println(JSONUtil.toJsonStr(chat));
		/*
{"id":"as-4b14j5prv2","object":"chat.completion","created":1691128216,"isTruncated":false,"result":"好的，这是通过递归方式实现的函数：\n\n\n```python\ndef fibonacci(n):\n    if n <= 1:\n        return n\n    else:\n        return fibonacci(n-1) + fibonacci(n-2)\n```\n这个函数使用递归来计算斐波那契数列的第 n 个数。当 n 小于或等于 1 时，返回 n。否则，递归地计算前两个数的和，并返回结果。","needClearHistory":false,"usage":{"promptTokens":42,"completionTokens":94,"totalTokens":136}}
		 */
    }

    @Test
    void contextLoads4() throws InterruptedException {
        // 对话
        List<MessageItem> messages = new ArrayList<>();
        messages.add(MessageItem.builder().role(MessageItem.Role.USER).content("帮我用Python实现计算斐波那契数列").build());
        messages.add(MessageItem.builder().role(MessageItem.Role.ASSISTANT).content("好的，这是通过循环方式实现的函数：...").build());
        messages.add(MessageItem.builder().role(MessageItem.Role.USER).content("你写的不好，请用递归方式实现").build());
        wenXinClient.streamChat(messages, new ConsoleExampleEventSourceListener(), ModelE.ERNIE_Bot);
        Thread.sleep(20_000);
		/*
		2023-08-04 13:51:09.269  INFO 5496 --- ...  : 建立sse连接...
		2023-08-04 13:51:09.277  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128268,"sentence_id":0,"is_end":false,"is_truncated":false,"result":"好的，这是通过递","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":7,"total_tokens":49}}
		2023-08-04 13:51:11.027  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128270,"sentence_id":1,"is_end":false,"is_truncated":false,"result":"归方式实现的函数：\n\n\n```python\ndef fibonacci(n):\n    if n \u003c= 1:\n        return n\n    else:\n        return fibonacci(n-1) + fibonacci(n-2)\n```\n这个函数使用","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":40,"total_tokens":89}}
		2023-08-04 13:51:11.068  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128270,"sentence_id":2,"is_end":false,"is_truncated":false,"result":"递归来计算斐波那契数列的第 n 项。","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":15,"total_tokens":104}}
。。。
		2023-08-04 13:51:13.644  INFO 5496 --- ...  : 返回数据结束了
		2023-08-04 13:51:13.644  INFO 5496 --- ...  : 关闭sse连接...
		*/
    }

```

