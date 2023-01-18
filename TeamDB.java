import java.util.Scanner;
import java.sql.*;

/**
   This program uses a database to keep track of
   summer T-ball teams, games, and season records.
*/

public class TeamDB
{
    public static void main(String[] args)
    {
	// Create a named constant for the database URL
	// NOTE: This value is specific for Java DB
	final String DB_URL = "jdbc:derby:TeamDB;" +
                              "create=true";

	Connection conn;

	try
	{
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

            char choice;
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Welcome to the Sports " +
                               "Teams Database Manager!");
            do
            {
		printMenu();
		choice = keyboard.nextLine().charAt(0);
		switch(choice)
		{
                    case '0':
                        // Close the connection
                        conn.close();
                        break;
                    case '1':
                        viewTeams(conn);
			break;
                    case '2':
			viewSchedule(conn);
			break;
                    case '3':
			addTeams(conn);
			break;
                    case '4':
			addGames(conn);
			break;
                    case '5':
			enterScores(conn);
			break;
                    case '6':
			beginNewSeason(conn);
			break;
		}
            } while(choice != '0');
	}

	catch(Exception ex)
	{
            System.out.println("ERROR: " + ex.getMessage());
	}
    }

    /**
    The printMenu method displays the menu choices
    for the user to work with the database.
    */

    public static void printMenu()
    {
	System.out.println();
	System.out.println("Select from the following " +
                           "options:");
	System.out.println("1. View team standings");
	System.out.println("2. View the schedule");
	System.out.println("3. Add a team");
	System.out.println("4. Add a game to the schedule");
	System.out.println("5. Enter game scores");
	System.out.println("6. Begin a new season");
	System.out.println("0. Exit the program");
    }

    /**
    The beginNewSeason method is a utility method that
    removes the tables and allows the user to reset the
    database for a new season.
    @param conn A connection to the database.
    */

    public static void beginNewSeason(Connection conn)
    {
	try
	{
            Statement stmt = conn.createStatement();

            // Remove the tables if they already exist
            // Throws an exception if the tables do not exist
            stmt.execute("DROP TABLE Games");
            stmt.execute("DROP TABLE Teams");

            // Once the tables have been removed, call the
            // method to create and initialize the tables
            System.out.println("Reinitializing database " +
                               "for a new season");
            createTeamDB(conn);
	}

	catch(Exception ex)
	{
            // Create the tables if they do not exist
            System.out.println("Creating database for " +
                               "the first time");
            createTeamDB(conn);
	}
    }

    /**
    The createTeamDB method is a utility method that
    creates the tables and initializes the database
    with teams and games.
    @param conn A connection to the database.
    */

    public static void createTeamDB(Connection conn)
    {
	try
	{
            Statement stmt = conn.createStatement();

            // Create the table of teams
            stmt.execute("CREATE TABLE Teams (" +
                         "TeamName CHAR(15) " +
                         "NOT NULL PRIMARY KEY, " +
                         "Wins INT, " + "Losses INT, " +
                         "Ties INT" + ")");

            // Add some teams
            stmt.executeUpdate("INSERT INTO Teams " +
                               "(TeamName) " +
                               "VALUES ('Astros')");

            stmt.executeUpdate("INSERT INTO Teams " +
                               "(TeamName) " +
                               "VALUES ('Marlins')");

            stmt.executeUpdate("INSERT INTO Teams " +
                               "(TeamName) " +
                               "VALUES ('Brewers')");

            stmt.executeUpdate("INSERT INTO Teams " +
                               "(TeamName) " +
                               "VALUES ('Cubs')");

            // Create a listing of the games to be played
            stmt.execute("CREATE TABLE Games (" +
                         "GameNumber INT " +
                         "NOT NULL PRIMARY KEY, " +
                         "HomeTeam CHAR(15) " +
                         "NOT NULL REFERENCES " +
                         "Teams (TeamName), " +
                         "HomeTeamScore INT, " +
                         "VisitorTeam CHAR(15) NOT NULL " +
                         "REFERENCES Teams (TeamName), " +
                         "VisitorTeamScore INT" + ")");

            stmt.executeUpdate("INSERT INTO Games " +
                               "(GameNumber, HomeTeam, " +
                               "VisitorTeam) " +
                               "VALUES (1, 'Astros', " +
                               "'Brewers')");

            stmt.executeUpdate("INSERT INTO Games " +
                               "(GameNumber, HomeTeam, " +
                               "VisitorTeam) " +
                               "VALUES (2, 'Brewers', " +
                               "'Cubs')");

            stmt.executeUpdate("INSERT INTO Games " +
                               "(GameNumber, HomeTeam, " +
                               "VisitorTeam) " +
                               "VALUES (3, 'Cubs', " +
                               "'Astros')");
	}

	catch (Exception ex)
	{
            System.out.println("ERROR: " + ex.getMessage());
	}
    }

    /**
    The addTeams method allows the user to add
    more teams to the database.
    @param conn A connection to the database.
    */

    public static void addTeams(Connection conn)
    {
	Scanner keyboard = new Scanner(System.in);

	try
	{
            char ans;
            String teamName;

            Statement stmt = conn.createStatement();

            do
            {
		// Prompt the user for a new team name
                System.out.print("Enter the new " +
                                 "team name: ");
		teamName = keyboard.nextLine();
                
		// Update the Teams table
                stmt.executeUpdate("INSERT INTO Teams " +
                                   "(TeamName) " +
                                   "VALUES ('" + teamName + "')");

		System.out.print("Do you want to enter " +
                                 "another team: ");
		ans = keyboard.nextLine().charAt(0);

            } 
            while(ans == 'Y'|| ans == 'y');
	}

	catch(Exception ex)
	{
            System.out.println("ERROR: " + ex.getMessage());
	}
    }

    /**
    The addGames method allows the user to add games to
    the schedule. A unique game number is created for
    each game on the schedule.The user will need to
    supply a home team name and a visitor team name
    from the keyboard.
    @param conn A connection to the database.
    */

    public static void addGames(Connection conn)
    {
	Scanner keyboard = new Scanner (System.in);

	try
	{
            char ans;
            String homeTeam;
            String visitingTeam;
            int gameNumber = 1;

            Statement stmt = conn.createStatement();

            // Retrieve the data and count the number of
            // games already scheduled so that you can
            // add a unique game number
            ResultSet result = stmt.executeQuery("SELECT * " +
                                                 "FROM " +
                                                 "Games");

            while(result.next())
            {
		gameNumber++;
            }

            do
            {
		System.out.print("Enter the home " +
                                 "team name: ");
		homeTeam = keyboard.nextLine();

		System.out.print("Enter the visiting " +
                                 "team name: ");
		visitingTeam = keyboard.nextLine();

		stmt.executeUpdate("INSERT INTO Games " +
                                   "(GameNumber, HomeTeam, " +
                                   "VisitorTeam) " +
                                   "VALUES (" + gameNumber +
                                   ", '" + homeTeam + "', '" +
                                   visitingTeam + "')");


		System.out.print("Do you want to enter " +
                                 "another game: ");
		ans = keyboard.nextLine().charAt(0);

            } while(ans == 'Y'|| ans == 'y');
	}

	catch(Exception ex)
	{
            System.out.println("ERROR: " + ex.getMessage());
	}
    }

    /**
    The viewTeams method displays a table listing the
    team names and season records. Since teams have not
    yet played, all numbers are zero.
    @param conn A connection to the database.
    */

    public static void viewTeams(Connection conn)
    {
	try
	{
            // Create a Statement object
            Statement stmt = conn.createStatement();

            // Send the statement to the DBMS
            ResultSet result = stmt.executeQuery("SELECT * " +
                                                 "FROM " +
                                                 "Teams");

            System.out.printf("%-15s %10s %10s %10s\n",
			"Team Name", "Win", "Lose",
                        "Tie");

            // Display the contents of the result set
            // The result set will have 5 columns
            while(result.next())
            {
		System.out.printf("%-15s %10d %10d %10d\n",
                result.getString("TeamName"),
                result.getInt("Wins"),
                result.getInt("Losses"),
                result.getInt("Ties"));
            }
	}

	catch(Exception ex)
	{
            System.out.println("ERROR: " + ex.getMessage());
	}
    }

    /**
    The viewSchedule method retrieves and displays the
    teams and scores for all games.
    @param conn A connection to the database.
    */

    public static void viewSchedule(Connection conn)
    {
	try
	{
            // Create a Statement object
            Statement stmt = conn.createStatement();

            // Create a string with a SELECT statement
            String gamesTable = "SELECT * FROM Games";
            // Send the statement to the DBMS
            ResultSet games = stmt.executeQuery(gamesTable);
            
            // This is a suggested column headings display
            System.out.println("List of games and scores:");
            System.out.printf("%-6s %-20s %6s     " +
                              "%-20s %6s\n",
                              "GameID", "Home", "Score",
                              "Visitor", "Score");

            // Use a while loop to display the result set
            // The result set will have five columns
            while(games.next())
            {
		System.out.printf("%-6s %-20s %6s %-20s %6s\n",
                games.getInt("GameNumber"),
                games.getString("HomeTeam"),
                games.getInt("HomeTeamScore"),
                games.getString("VisitorTeam"),
                games.getInt("VisitorTeamScore"));
            }
	}

	catch(Exception ex)
	{
            System.out.println("ERROR: " + ex.getMessage());
	}
    }

    /**
    The enterScores method allows user to enter scores
    for both teams. The method will update the Games
    table with the scores entered. It will also compare
    the scores to determine the winning and losing teams
    (or tie) and update the appropriate column in the
    Teams table for each team involved in the game.
    @param conn A connection to the database.
    */

    public static void enterScores(Connection conn)
    {
	Scanner keyboard = new Scanner(System.in);
	try
	{
            char ans;
            int gameNumber;
            String homeTeam;
            String visitingTeam;
            int score1;
            int score2;
            String sqlStatement;
            ResultSet result;

            Statement stmt = conn.createStatement();
            
            do
            {
		viewSchedule(conn);

		System.out.print("Enter the game ID: ");
		gameNumber = keyboard.nextInt();

		// Get the result set from a query that
		// selects all information for the gameNumber
		// the user entered
                sqlStatement = "SELECT * FROM Games WHERE GameNumber = "
                                    + gameNumber;
                result = stmt.executeQuery(sqlStatement);

		if(result.next())
		{
                    homeTeam = result.getString("HomeTeam");
                    visitingTeam = result.getString("Visitor" +
                                                    "Team");

                    System.out.print("Enter the score " +
                                     "for the " + homeTeam);
                    score1 = keyboard.nextInt();

                    System.out.print("Enter the score " +
                                     "for the " + visitingTeam);
                    score2 = keyboard.nextInt();
                    keyboard.nextLine();

                    // Execute an update to the Games table to
                    // store the score for each team of that
                    // game number
                    stmt.executeUpdate("UPDATE Games SET HomeTeamScore = "
                                    + score1 + ", VisitorTeamScore = "
                                    + score2 + " WHERE GameNumber = " 
                                    + gameNumber);
                        
                    if(score1 < score2)
                    {
                        // Retrieve the number from the
			// appropriate column
			// (wins, losses, or ties) for the
                        // home team, increment, and update
			// that team's record.
			// Do the same for the visiting team
                        
                        int lossesHomeTeam;
                        int winsVisitingTeam;
                        ResultSet resultVisiting = stmt.executeQuery("SELECT "
                                + "* FROM Teams WHERE TeamName = '" 
                                + visitingTeam + "'");
                        
                        if(resultVisiting.next())
                        {
                            winsVisitingTeam = resultVisiting.getInt("Wins");
                           
                            winsVisitingTeam++;
                        
                            stmt.executeUpdate("UPDATE Teams SET Wins = "
                                        + winsVisitingTeam + " WHERE TeamName "
                                                + "= '" + visitingTeam + "'");
                        }
                        
                        ResultSet resultHome = stmt.executeQuery("SELECT * "
                                + "FROM Teams WHERE TeamName = '" + homeTeam 
                                + "'");
                        
                        if(resultHome.next())
                        {
                            lossesHomeTeam = resultHome.getInt("Losses");
                            
                            lossesHomeTeam++;
              
                            stmt.executeUpdate("UPDATE Teams SET Losses = "
                                        + lossesHomeTeam + " WHERE TeamName = '"
                                        + homeTeam + "'");
                        }
                    }
                    else if(score2 < score1)
                    {
			// Retrieve the number from the
			// appropriate column
			// (wins, losses, or ties) for the
			// home team, increment, and update
			// that team's record.
			// Do the same for the visiting team
                        int winsHomeTeam;
                        int lossesVisitingTeam;
                
                        ResultSet resultVisiting = stmt.executeQuery("SELECT "
                                + "* FROM Teams WHERE TeamName = '" + homeTeam 
                                + "'");
                        
                        if(resultVisiting.next())
                        {
                            winsHomeTeam = resultVisiting.getInt("Wins");
                            winsHomeTeam++;
                        
                            stmt.executeUpdate("UPDATE Teams SET Wins = "
                                        + winsHomeTeam + " WHERE TeamName = '"
                                        + homeTeam + "'");
                        }
                        
                        ResultSet resultHome = stmt.executeQuery("SELECT * "
                                + "FROM Teams WHERE TeamName = '" + 
                                visitingTeam + "'");
                        
                        if(resultHome.next())
                        {
                            lossesVisitingTeam = resultHome.getInt("Losses");
                            lossesVisitingTeam++;
              
                            stmt.executeUpdate("UPDATE Teams SET Losses = "
                                        + lossesVisitingTeam + " WHERE TeamName "
                                                + "= '" + visitingTeam + "'");
                        } 
                    }
                    else
                    {
			// Retrieve the number from the
			// appropriate column
			// (wins, losses, or ties) for the
			// home team, increment, and update
			// that team's record.
			// Do the same for the visiting team
                        int tiesHomeTeam;
                        int tiesVisitingTeam;
                       
                        ResultSet resultVisiting = stmt.executeQuery("SELECT "
                                + "* FROM Teams WHERE TeamName = '" + 
                                visitingTeam + "'");
                        
                        if(resultVisiting.next())
                        {
                            tiesVisitingTeam = resultVisiting.getInt("Ties");
                            tiesVisitingTeam++;
                        
                            stmt.executeUpdate("UPDATE Teams SET Ties = "
                                        + tiesVisitingTeam + " WHERE TeamName "
                                                + "= '" + visitingTeam + "'");
                        }
                        
                        ResultSet resultHome = stmt.executeQuery("SELECT "
                                + "* FROM Teams WHERE TeamName = '" 
                                + homeTeam + "'");
                        
                        if(resultHome.next())
                        {
                            tiesHomeTeam = resultHome.getInt("Ties");
                            tiesHomeTeam++;
              
                            stmt.executeUpdate("UPDATE Teams SET Ties = "
                                        + tiesHomeTeam + " WHERE TeamName = '"
                                        + homeTeam + "'");
                        }
                    }
		}

		System.out.print("Do you want to enter " +
                                 "another game: ");
		ans = keyboard.nextLine().charAt(0);

            } 
            while(ans == 'Y'|| ans == 'y');
	}

	catch(Exception ex)
	{
            System.out.println("ERROR: " + ex.getMessage());
	}
    }
}

