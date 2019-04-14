package data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Uri {
    JSONPLACEHOLDER("https://jsonplaceholder.typicode.com");

    private final String uri;
}
