import java.util.ArrayList;

class GitCommands {
    WorkingDirectory working_directory;
    ArrayList<String> staging = new ArrayList<>();
    ArrayList<String> local_repository_message = new ArrayList<>();
    ArrayList<Object> local_repository_files = new ArrayList<>();

    public GitCommands(WorkingDirectory working_directory) {
        this.working_directory = working_directory;
    }

    // Command: git init
    public void init() {
        System.out.println("Initialized as empty Git repository.");
    }

    // Command: git status
    public String status() {
       java.util.LinkedHashSet<String> allFiles = new java.util.LinkedHashSet<>();
    allFiles.addAll(this.staging);
    allFiles.addAll(this.working_directory.new_changes);

    int totalChanges = allFiles.size();

    StringBuilder statusMessage = new StringBuilder();
    statusMessage.append("You have ").append(totalChanges).append(" change/s.\n");

    for (String file : allFiles) {
        statusMessage.append(file).append("\n");
    }

    return statusMessage.toString();
}

    // Command: git add <filename/file directory/wildcard>
    public void add(String path_file) {
        ArrayList<String> modified_files = this.working_directory.new_changes;
        // System.out.println(modified_files.toString());
        for (int i = 0; i < modified_files.size(); i++) {
            if (modified_files.get(i).equals(path_file)) {
                this.staging.add(modified_files.get(i));
                modified_files.remove(i);
            }
        }
        // System.out.println(modified_files.toString());
    }

    // Command: git commit -m "<message>"
    public String commit(String message) {
        if (this.staging.size() > 0) {
            this.local_repository_message.add(message);
            this.local_repository_files.add(this.staging);

            this.staging.clear();
            // System.out.println(message);
            return "Done committing to local repository.";
        }
        return "Nothing to commit.";
    }

    // // Command: git push
    public String push() {
        if (this.local_repository_files.size() > 0) {
            return "Done pushing to remote repository.";
        } else {
            return "Nothing to push. No committed file found.";
        }
    }
}
