package org.example.elevator.request;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RequestStore {

    private final LinkedList<Request> requests = new LinkedList<>();

    private final ReentrantLock lock = new ReentrantLock();

    public boolean isEmpty(){
        return executeInLock(requests::isEmpty);
    }

    public boolean removeRequest(Predicate<Request> remove) {
        return executeInLock(() -> requests.removeIf(remove));
    }

    public void addRequest(Request request){
        executeInLock(() -> {
            if(!requests.contains(request))
                requests.add(request);
        });
    }

    public List<Request> getAllRequests(){
        return requests;
    }

    public Request getFirst(){
        return requests.getFirst();
    }

    private <T> T executeInLock(Supplier<T> supplier){
        try {
            lock.lock();
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }

    private void executeInLock(Runnable runnable){
        try {
            lock.lock();
            runnable.run();
        } finally {
            lock.unlock();
        }
    }
}
