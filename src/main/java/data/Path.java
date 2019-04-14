package data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Path {
    POSTS("/posts"),
    COMMENTS("/comments");

    private final String path;
}
