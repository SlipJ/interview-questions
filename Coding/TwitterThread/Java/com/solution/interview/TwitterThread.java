package com.solution.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The task is to publish a long text in a "Twitter thread" format.
 * Message has a predefined maximum size.
 * If text is small enough to fit to single message - no pagination is needed.
 * If not each message must include its page as a part of the message in format "xx/yy", where
 * xx - number of page, yy - total number of pages
 * It's not allowed to break the words. If part of the word doesn't fit to the message,
 * the whole word must be moved to the next message in thread
 * The total size of thread is limited to 99 pages.
 */

public class TwitterThread {
    public static final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
            "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
            "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
            "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " +
            "sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur " +
            "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
            "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis " +
            "aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est " +
            "laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
            "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa " +
            "qui officia deserunt mollit anim id est laborum.";
    public static int messageMaxSize = 100;
    public static int maxThreadSize = 99;

    public static void main(String[] args) {
        List<String> thread = createThread(text);
        thread.forEach(System.out::println);
    }

    /**
     * Start here
     *
     * @param text original text
     * @return List of text messages
     */

    private static List<String> createThread(String text) {
        // check if pagination is required
        if (text.length() <= messageMaxSize) {
            return Collections.singletonList(text);
        }
        List<String> messagesWithoutPages = splitToMessages(text);
        return addPages(messagesWithoutPages);
    }

    private static List<String> splitToMessages(String text) {
        int effectiveSize = messageMaxSize - 6; // format: " 05/85"
        String[] words = text.split(" ");
        List<String> result = new ArrayList<>();
        String currentMessage = "";
        for(String word: words) {
            if (word.length() > effectiveSize) {
                throw new RuntimeException("Word too long");
            }
            if (currentMessage.equals("")) {
                // First message in thread
                currentMessage = word;
                continue;
            }
            if (currentMessage.length() + 1 + word.length() < effectiveSize) {
                // Append word to current message
                currentMessage = String.format("%s %s", currentMessage, word);
            } else {
                // Finish message and start next
                result.add(currentMessage);
                currentMessage = word;
            }
        }
        return result;
    }

    private static List<String> addPages(List<String> messagesWithoutPages) {
        int threadSize = messagesWithoutPages.size();
        if (threadSize > maxThreadSize) {
            throw new RuntimeException("Thread too long");
        }
        return IntStream.range(0, threadSize)
                .boxed()
                .flatMap(index -> Stream.of(index)
                        .map(i -> toPage(i, threadSize)) // Create page suffix
                        .map(page -> String.format("%s %s", messagesWithoutPages.get(index), page)))  // Append page suffix
                .collect(Collectors.toList());
    }

    private static String toPage(Integer i, int threadSize) {
        String threadSizeStr = threadSize < 10 ? String.format("0%s", threadSize) : String.valueOf(threadSize);
        if (i+1 < 10) {
            return String.format("0%s/%s", i+1, threadSizeStr);
        } else {
            return String.format("%s/%s", i+1, threadSizeStr);
        }
    }
}
