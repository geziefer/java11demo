package de.syrocon.java11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Demo {

	public static void main(String[] args) throws Exception {
		// show JEP 323: Local-Variable Syntax for Lambda Parameters
		List<String> list = List.of("A", "b", "CD");
		list = list.stream().map((@NotNull @Size(min = 2, max = 2) var s) -> s.toLowerCase())
				.collect(Collectors.toList());
		list.stream().forEach(System.out::println);

		// show JEP 321: HTTP Client (Standard)
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder(new URI("https://syrocon.de/")).GET().build();
		CompletableFuture<HttpResponse<String>> fr = client.sendAsync(request, BodyHandlers.ofString());
		while (!fr.isDone()) {
			System.out.println("Waiting...");
			Thread.sleep(10);
		}
		HttpResponse<String> response = fr.get();
		System.out.println("Done: " + response.statusCode());
		response.headers().map().values().stream().forEach(v -> System.out.println(v));
		System.out.println(response.body());

		// show JEP 181: Nest-Based Access Control
		Arrays.asList(Demo.class.getNestMembers()).stream().forEach(System.out::println);
		System.out.println(Demo.Nest.class.getNestHost());
	}

	class Nest {

	}

}