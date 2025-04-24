# Core Concepts

## Asynchronous Computation
Asynchronous computation allows you to run computations in the background (often on different threads) without blocking the main thread of execution.

## Future Result
A future result holds a value (or an exception) that will be produced by the asynchronous computation at some point in the future.

## Completable
Unlike the basic `Future`, you can explicitly complete a `CompletableFuture` manually (programmatically set its value or exception). This is useful for bridging callback-based APIs with the `CompletableFuture` model.

## Composable (Callbacks & Chaining)
This is where `CompletableFuture` truly shines. It provides a rich set of methods to:
- Register callbacks that execute when the future completes (e.g., `thenApply`, `thenAccept`, `thenRun`).
- Chain multiple asynchronous operations together in a non-blocking way.
- Combine results from multiple `CompletableFuture` instances (e.g., `thenCombine`, `allOf`, `anyOf`).
- Handle exceptions gracefully (e.g., `exceptionally`, `handle`).

# Why was it needed? Limitations of `java.util.concurrent.Future`

## Blocking `get()`
The primary way to retrieve the result from a `Future` is `get()`, which blocks the calling thread until the result is available. This negates many benefits of asynchronous programming.

## No Callbacks
`Future` doesn't provide a direct way to attach a callback function that gets executed automatically when the result is ready. You typically have to poll (`isDone()`) or block (`get()`).

## No Chaining/Composition
You can't easily say "when this future completes, start this next asynchronous task using its result."

## Limited Exception Handling
Exception handling usually involves catching `ExecutionException` when calling `get()`, which can be cumbersome.

## Not Manually Completable
A standard `Future` represents a task submitted to an `ExecutorService`, and you can't manually complete it with a value from outside that task.

# Key `CompletableFuture` Methods (Common Patterns)

## Creating a `CompletableFuture`
- `CompletableFuture.supplyAsync(Supplier<U> supplier)`: Runs the supplier asynchronously (usually on the `ForkJoinPool.commonPool()` or a specified `Executor`) and returns a `CompletableFuture` that will complete with the supplier's result.
- `CompletableFuture.runAsync(Runnable runnable)`: Runs the runnable asynchronously. Returns a `CompletableFuture<Void>` (completes with `null` when the runnable finishes).
- `new CompletableFuture<T>()`: Creates an incomplete `CompletableFuture` that you can complete later using `complete()` or `completeExceptionally()`.

## Callbacks / Chaining (when the future completes)
- `thenApply(Function<? super T,? extends U> fn)`: Takes the result of the completed future, applies the function `fn` to it, and returns a new `CompletableFuture` with the function's result. (Transforms the value).
- `thenAccept(Consumer<? super T> action)`: Takes the result, performs the consumer action with it, and returns `CompletableFuture<Void>`. (Consumes the value).
- `thenRun(Runnable action)`: Doesn't take the result; simply runs the runnable action when the future completes. Returns `CompletableFuture<Void>`. (Action after completion).

### *Async versions
Methods like `thenApplyAsync`, `thenAcceptAsync`, `thenRunAsync` exist. They allow you to specify an `Executor` for the callback to run on, or default to the `ForkJoinPool.commonPool()`. This prevents potentially long-running callbacks from blocking the thread that completed the previous stage.

## Combining Futures
- `thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)`: Waits for both this future and the other future to complete, then applies the `BiFunction` to their results. Returns a new `CompletableFuture` with the combined result.
- `thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action)`: Waits for both, then consumes both results.
- `runAfterBoth(CompletionStage<?> other, Runnable action)`: Waits for both, then runs the action.
- `applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)`: Waits for either this future or the other future to complete first, applies the function to the result of the one that finished first.
- `acceptEither(...)`, `runAfterEither(...)`: Similar "either" logic for consuming or running an action.
- `CompletableFuture.allOf(CompletableFuture<?>... cfs)`: Returns a `CompletableFuture<Void>` that completes only when all of the given futures complete. Useful for waiting for multiple independent tasks.
- `CompletableFuture.anyOf(CompletableFuture<?>... cfs)`: Returns a `CompletableFuture<Object>` that completes when any of the given futures complete, with the result of that first completed future.

## Exception Handling
- `exceptionally(Function<Throwable, ? extends T> fn)`: If the future completes exceptionally (with an error), this function is called with the `Throwable`. It can return a default value or transformed exception, allowing the chain to recover. Returns a new `CompletableFuture`.
- `handle(BiFunction<? super T, Throwable, ? extends U> fn)`: Called whether the future completes normally (with a result `T`) or exceptionally (with a `Throwable`). Exactly one of the two arguments to the `BiFunction` will be non-null. Allows handling both success and failure in one place. Returns a new `CompletableFuture`.

## Getting the Result (Use with Caution - Prefer Callbacks)
- `get()`: Blocks indefinitely until the future completes. Throws checked exceptions (`InterruptedException`, `ExecutionException`).
- `get(long timeout, TimeUnit unit)`: Blocks for a specified timeout.
- `join()`: Similar to `get()`, but throws unchecked exceptions (`CompletionException`, `CancellationException`), making it slightly easier to use within lambda expressions. Still blocks!
- `getNow(T valueIfAbsent)`: Returns the result immediately if available, otherwise returns the provided default value. Does not block.