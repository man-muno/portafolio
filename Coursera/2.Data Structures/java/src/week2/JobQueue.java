package week2;

import java.io.*;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;
    
    private Heap<Worker> workers;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
        
        workers = new Heap<JobQueue.Worker>(numWorkers);
        for(int i=0;i<numWorkers;i++){
        	workers.load(new Worker(i,0));
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // create data structures
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        for (int i = 0; i < jobs.length; i++) {
        	//get duration of next job
            int duration = jobs[i];
            
            //variable for the next available thread
            Worker priority = workers.getPriorityWorker();
            //add the current thread to the assigned worker list
            assignedWorker[i] = priority.getId();
            //set start time for the current thread
            startTime[i] = priority.getPriority();
            //set the duration of the job to the next free time
            priority.setPriority(priority.getPriority() + duration);
            workers.add(priority);
                   
        }
        
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    
    public class Worker implements Comparable{

    	private int id;
    	
    	private long priority;
    	  	
		public Worker(int id, int priority) {
			this.id = id;
			this.priority = priority;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public long getPriority() {
			return priority;
		}

		public void setPriority(long priority) {
			this.priority = priority;
		}

		@Override
		public int compareTo(Object o) {
			Worker w = (Worker)o;
			return priority < w.getPriority() ? -1 : priority > w.getPriority() ? 1 : 0 ;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", priority=" + priority + "]";
		}

  	
		
		
    }
    
    
}
