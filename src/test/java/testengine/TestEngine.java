package testengine;

import org.testng.annotations.Test;
import executionengine.ExecutionEngine;

public class TestEngine 
{
	public ExecutionEngine exec;
	@Test
	public void testEngine()
	{
		exec = new ExecutionEngine();
		exec.startExecution("login");
	}

}
