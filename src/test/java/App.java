


import java.util.ArrayList;
import java.util.List;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Comment;
import net.rcarz.jiraclient.CustomFieldOption;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.Project;


/**
 * Entry-point invoked when the jar is executed.
 */
public class App
{
    private static final String JIRA_URL = "https://jira3.technisat-digital.de/";
    private static final String JIRA_ADMIN_USERNAME = "boris.schneider";
    private static final String JIRA_ADMIN_PASSWORD = "Carconnect1!";

    public static void main(String[] args) throws Exception
    {
    	 BasicCredentials creds = new BasicCredentials(JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD);
         JiraClient jira = new JiraClient(JIRA_URL, creds);

         try {
        	 // jira.getRestClient().Initiate();
        	 
        	 for (Project aProject: jira.getProjects())
        		 System.out.println(aProject.getId() + " - " + aProject.getDescription());
        	 
             /* Retrieve issue TEST-123 from JIRA. We'll get an exception if this fails. */
             final Issue issue = jira.getIssue("MIB3REQ-10208");

             /* Print the issue key. */
             System.out.println(issue);

             /* You can also do it like this: */
             System.out.println(issue.getKey());

             /* Print the reporter's username and then the display name */
             System.out.println("Reporter: " + issue.getReporter());
             System.out.println("Reporter's Name: " + issue.getReporter().getDisplayName());

             /* Print existing labels (if any). */
             for (String l : issue.getLabels())
                 System.out.println("Label: " + l);

             /* Print the summary. We have to refresh first to pickup the new value. */
             // issue.refresh();
             System.out.println("New Summary: " + issue.getSummary());

             /* Add the first comment and update it */
             List<Comment> aList = issue.getComments();

             /* Pretend customfield_1234 is a text field. Get the raw field value... */
             Object cfvalue = issue.getField("customfield_10804");

             /* ... Convert it to a string and then print the value. */
             String cfstring = Field.getString(cfvalue);
             System.out.println(cfstring);

             /* Search for issues */
             Issue.SearchResult sr = jira.searchIssues("assignee=batman");
             System.out.println("Total: " + sr.total);
             for (Issue i : sr.issues)
                 System.out.println("Result: " + i);

             /* Search with paging (optionally 10 issues at a time). There are optional
                arguments for including/expanding fields, and page size/start. */
             Issue.SearchResult sr1 = jira.searchIssues("project IN (GOTHAM) ORDER BY id");
             while (((List<String>) sr1).iterator().hasNext())
                 System.out.println("Result: " + ((List<String>) sr1).iterator().next());

         } catch (JiraException ex) {
             System.err.println(ex.getMessage() );
             if (ex.getCause() != null) System.err.println(ex.getCause());
             ex.printStackTrace();
             
                 
         }
     }
}