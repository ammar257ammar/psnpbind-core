/**
* Binding Pocket SNPs' effect on Binding Affinity Database Project (PSnpBind)
* 
*Copyright (C) 2019-2021  Ammar Ammar <ammar257ammar@gmail.com> ORCID:0000-0002-8399-8990
*
*This program is free software: you can redistribute it and/or modify
*it under the terms of the GNU Affero General Public License as published by
*the Free Software Foundation, either version 3 of the License, or
*(at your option) any later version.
*
*This program is distributed in the hope that it will be useful,
*but WITHOUT ANY WARRANTY; without even the implied warranty of
*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*GNU Affero General Public License for more details.
*
*You should have received a copy of the GNU Affero General Public License
*along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package io.github.ammar257ammar.psnpbind.core;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * A command line interface class to parse the command line parameters and execute the corresponding tasks
 * 
 * @author Ammar Ammar
 *
 */

@Command(name = "psbap")
public class CliOptions {

	@Option(names = {"-h", "-?", "--help" }, usageHelp = true, description = "Display a help message")
	boolean help = false;

	@Option(names = {"-op", "--operation"}, description = "select an operation to perform: init, pocket-snps-mapping-and-foldx-prep, foldx-report, prepare-ligands-folders, ligands-tanimoto-dataset, prepare-vina-folders-config, generate-dockings-results, build-database", required = true)
	String operation = "";

	CliOptions(String[] args) {
		try {
			CliOptions cliOptions = CommandLine.populateCommand(this, args);
			
			if (cliOptions.help) {
				new CommandLine(this).usage(System.out);
				System.exit(0);
			}
			
		} catch (CommandLine.ParameterException pe) {
			System.out.println(pe.getMessage());
			new CommandLine(this).usage(System.out);
			System.exit(64);
		}
	}
}
