package com.example.base.streamable;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;

import java.util.Iterator;

@RequiredArgsConstructor(staticName = "of")
class Products implements Streamable<Product> {

    private final Streamable<Product> streamable;

    @Override
    public Iterator<Product> iterator() {
        return streamable.iterator();
    }
}
