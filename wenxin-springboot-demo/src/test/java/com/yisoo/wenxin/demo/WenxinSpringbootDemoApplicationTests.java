package com.yisoo.wenxin.demo;

import cn.hutool.json.JSONUtil;
import com.yisoo.wenxin.WenXinClient;
import com.yisoo.wenxin.constant.ModelE;
import com.yisoo.wenxin.entity.ChatResponse;
import com.yisoo.wenxin.entity.MessageItem;
import com.yisoo.wenxin.sse.ConsoleExampleEventSourceListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WenxinSpringbootDemoApplicationTests {

    @Autowired
    WenXinClient wenXinClient;

    /**
     * （1）messages成员不能为空，1个成员表示单轮对话，多个成员表示多轮对话
     * （2）最后一个message为当前请求的信息，前面的message为历史对话信息
     * （3）必须为奇数个成员，成员中message的role必须依次为user、assistant
     * （4）最后一个message的content长度（即此轮对话的问题）不能超过2000个字符；
     * 如果messages中content总长度大于2000字符，系统会依次遗忘最早的历史会话，直到content的总长度不超过2000个字符
     */
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
		2023-08-04 13:46:10.859  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127970,"sentence_id":3,"is_end":false,"is_truncated":false,"result":"作为输入，并返回一个包含前n个斐波那契数列的列表。","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":23,"total_tokens":128}}
		2023-08-04 13:46:11.255  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127970,"sentence_id":4,"is_end":false,"is_truncated":false,"result":"如果n小于或等于0，则返回一个空列表。","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":17,"total_tokens":145}}
		2023-08-04 13:46:11.450  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127970,"sentence_id":5,"is_end":false,"is_truncated":false,"result":"如果n等于1，则返回一个只包含0的列表。","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":18,"total_tokens":163}}
		2023-08-04 13:46:12.255  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127971,"sentence_id":6,"is_end":false,"is_truncated":false,"result":"\n\n否则，我们初始化一个包含前两个斐波那契数的列表[0, 1]，然后使用一个循环来计算剩余的数字。","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":40,"total_tokens":203}}
		2023-08-04 13:46:12.853  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127972,"sentence_id":7,"is_end":false,"is_truncated":false,"result":"在每次迭代中，我们将前两个数字相加，并将结果添加到列表的末尾。","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":28,"total_tokens":231}}
		2023-08-04 13:46:13.652  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127972,"sentence_id":8,"is_end":false,"is_truncated":false,"result":"这个过程一直持续到我们计算出前n个数字。","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":19,"total_tokens":250}}
		2023-08-04 13:46:14.452  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127973,"sentence_id":9,"is_end":false,"is_truncated":false,"result":"\n\n要使用这个函数，只需要调用它并传入你想要的斐波那契数列的项数，例如：\n\n\n```python\nprint(fibonacci(10))  # 将输出 [0, 1, 1, 2","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":44,"total_tokens":294}}
		2023-08-04 13:46:15.256  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127974,"sentence_id":10,"is_end":false,"is_truncated":false,"result":", 3, 5, 8, 13, 21, 34]\n```\n这将输出前10个斐波那契数列的数字。","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":25,"total_tokens":319}}
		2023-08-04 13:46:15.451  INFO 9832 --- ...: 返回数据：{"id":"as-hu9m9cwazm","object":"chat.completion","created":1691127974,"sentence_id":11,"is_end":true,"is_truncated":false,"result":"","need_clear_history":false,"usage":{"prompt_tokens":14,"completion_tokens":0,"total_tokens":319}}
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
		2023-08-04 13:51:11.269  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128270,"sentence_id":3,"is_end":false,"is_truncated":false,"result":"如果 n 小于等于 1，则返回 n。","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":12,"total_tokens":116}}
		2023-08-04 13:51:12.268  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128271,"sentence_id":4,"is_end":false,"is_truncated":false,"result":"否则，返回前两项的和，即 fibonacci(n-1) + fibonacci(n-2)。递归过程会一直持续到 n \u003c= 1，然后逐级返回，直到计算出第 n 项为止。","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":49,"total_tokens":165}}
		2023-08-04 13:51:12.874  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128272,"sentence_id":5,"is_end":false,"is_truncated":false,"result":"\n\n注意：递归方式虽然简洁，但在计算较大的数时可能会导致栈溢出。","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":27,"total_tokens":192}}
		2023-08-04 13:51:13.642  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128272,"sentence_id":6,"is_end":false,"is_truncated":false,"result":"因此，在实际使用中，建议使用循环方式实现。","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":18,"total_tokens":210}}
		2023-08-04 13:51:13.644  INFO 5496 --- ...  : 返回数据：{"id":"as-dv5pdk0jm6","object":"chat.completion","created":1691128272,"sentence_id":7,"is_end":true,"is_truncated":false,"result":"","need_clear_history":false,"usage":{"prompt_tokens":42,"completion_tokens":0,"total_tokens":210}}
		2023-08-04 13:51:13.644  INFO 5496 --- ...  : 返回数据结束了
		2023-08-04 13:51:13.644  INFO 5496 --- ...  : 关闭sse连接...
		*/
    }
}
