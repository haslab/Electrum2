package org.alloytools.alloy.core;

import java.io.PrintStream;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.ErrorWarning;

/**
 * @modified Nuno Macedo // [electrum-temporal] updated reporting;
 *           [electrum-decomposed] updated reporting;
 */
public class TestReporter extends A4Reporter {

    PrintStream out = System.out;

    @Override
    public void debug(String msg) {
        out.println("DEBG " + msg);
    }

    /**
     * This method is called by the parser to report parser events.
     */
    @Override
    public void parse(String msg) {
        out.println("PARS " + msg);
    }

    /**
     * This method is called by the typechecker to report the type for each
     * field/function/predicate/assertion, etc.
     */
    @Override
    public void typecheck(String msg) {
        out.println("TYPC " + msg);
    }

    /**
     * This method is called by the typechecker to report a nonfatal type error.
     */
    @Override
    public void warning(ErrorWarning msg) {
        out.println("WARN " + msg);
    }

    /**
     * This method is called by the ScopeComputer to report the scope chosen for
     * each sig.
     */
    @Override
    public void scope(String msg) {
        out.println("SCOP " + msg);
    }

    /**
     * This method is called by the BoundsComputer to report the bounds chosen for
     * each sig and each field.
     */
    @Override
    public void bound(String msg) {
        out.println("BOND " + msg);
    }

    /**
     * This method is called by the translator just before it begins generating CNF.
     *
     * @param solver - the solver chosen by the user (eg. SAT4J, MiniSat...)
     * @param bitwidth - the integer bitwidth chosen by the user
     * @param maxseq - the scope on seq/Int chosen by the user
     * @param mintrace - the minimum trace length
     * @param maxtrace - the maximum trace length
     * @param skolemDepth - the skolem function depth chosen by the user (0, 1,
     *            2...)
     * @param symmetry - the amount of symmetry breaking chosen by the user (0...)
     */
    @Override
    public void translate(String solver, int bitwidth, int maxseq, int mintrace, int maxtrace, int skolemDepth, int symmetry, String strat) {
        out.printf("TRNS %s steps=%s..%s bitwidth=%s maxseq=%s skolem=%s symmetry=%s mode=%s%n", solver, mintrace, maxtrace, bitwidth, maxseq, skolemDepth, symmetry, strat);
    }

    /**
     * This method is called by the translator just after it generated the CNF.
     *
     * @param primaryVars - the total number of primary variables
     * @param totalVars - the total number of variables including the number of
     *            primary variables
     * @param clauses - the total number of clauses
     */
    @Override
    public void solve(int step, int primaryVars, int totalVars, int clauses) {
        out.printf("SOLV primary=%s total=%s clauses=%s%n", primaryVars, totalVars, clauses);
    }

    /**
     * If solver==KK or solver==CNF, this method is called by the translator after
     * it constructed the Kodkod or CNF file.
     *
     * @param filename - the Kodkod or CNF file generated by the translator
     */
    @Override
    public void resultCNF(String filename) {
        out.println("CNF  " + filename);
    }

    /**
     * If solver!=KK and solver!=CNF, this method is called by the translator if the
     * formula is satisfiable.
     *
     * @param command - this is the original Command used to generate this solution
     * @param solvingTime - this is the number of milliseconds the solver took to
     *            obtain this result
     * @param solution - the satisfying A4Solution object
     */
    @Override
    public void resultSAT(Object command, long solvingTime, Object solution) {
        out.printf("RSAT %s, time=%s solution=%s%n", command, solvingTime, solution);
    }

    /**
     * If solver!=KK and solver!=CNF, this method is called by the translator before
     * starting the unsat core minimization.
     *
     * @param command - this is the original Command used to generate this solution
     * @param before - the size of the unsat core before calling minimization
     */
    @Override
    public void minimizing(Object command, int before) {
        out.printf("MINM %s before=%s%n", command, before);
    }

    /**
     * If solver!=KK and solver!=CNF, this method is called by the translator after
     * performing the unsat core minimization.
     *
     * @param command - this is the original Command used to generate this solution
     * @param before - the size of the unsat core before calling minimization
     * @param after - the size of the unsat core after calling minimization
     */
    @Override
    public void minimized(Object command, int before, int after) {
        out.printf("MINZ %s before=%s after=%s%n", command, before, after);
    }

    /**
     * If solver!=KK and solver!=CNF, this method is called by the translator if the
     * formula is unsatisfiable.
     *
     * @param command - this is the original Command used to generate this solution
     * @param solvingTime - this is the number of milliseconds the solver took to
     *            obtain this result
     * @param solution - the unsatisfying A4Solution object
     */
    @Override
    public void resultUNSAT(Object command, long solvingTime, Object solution) {
        out.printf("UNST %s solvingTime=%s solution=%s%n", command, solvingTime, solution);
    }

    /**
     * This method is called by the A4SolutionWriter when it is writing a particular
     * sig, field, or skolem.
     */
    @Override
    public void write(Object expr) {
        out.printf("WRIT %s%n", expr);
    }

}
