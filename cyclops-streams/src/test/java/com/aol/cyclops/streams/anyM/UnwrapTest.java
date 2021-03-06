package com.aol.cyclops.streams.anyM;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.aol.cyclops.monad.AnyM;

public class UnwrapTest {

	@Test
	public void unwrap(){
		Stream<String> stream = AnyM.streamOf("hello","world").asSequence().stream();
		assertThat(stream.collect(Collectors.toList()),equalTo(Arrays.asList("hello","world")));
	}
	
	@Test
	public void unwrapOptional(){
		Optional<List<String>> stream = AnyM.streamOf("hello","world")
											.asSequence()
											.toOptional();
		assertThat(stream.get(),equalTo(Arrays.asList("hello","world")));
	}
	@Test
	public void unwrapOptionalEmpty(){
		Optional<List<String>> opt = AnyM.streamOf(Optional.empty())
											.<String>toSequence()
											.toOptional();
		System.out.println(opt);
		assertFalse(opt.isPresent());
	}
	@Test
	public void unwrapOptionalList(){
		Optional<List<String>> stream =AnyM.fromOptional(Optional.of(Arrays.asList("hello","world")))
												.<String>toSequence()
												.toOptional();
		assertThat(stream.get(),equalTo(Arrays.asList("hello","world")));
	}
	@Test
	public void unwrapCompletableFuture(){
		CompletableFuture<List<String>> cf = AnyM.streamOf("hello","world")
											.asSequence()
											.toCompletableFuture();
		assertThat(cf.join(),equalTo(Arrays.asList("hello","world")));
	}
	@Test
	public void unwrapCompletableFutureList(){
		CompletableFuture<List<String>> cf = AnyM.fromCompletableFuture(CompletableFuture.completedFuture(Arrays.asList("hello","world")))
												.<String>toSequence()
												.toCompletableFuture();
		assertThat(cf.join(),equalTo(Arrays.asList("hello","world")));
	}
}
