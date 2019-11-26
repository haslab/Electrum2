/* Alloy Analyzer 4 -- Copyright (c) 2006-2009, Felix Chang
 * Electrum -- Copyright (c) 2015-present, Nuno Macedo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files
 * (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF
 * OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package edu.mit.csail.sdg.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.alloy4.Pos;
import edu.mit.csail.sdg.alloy4.TableView;

/**
 * Immutable; represents a LET or QUANTIFICATION variable in the AST.
 * <p>
 * <b>Invariant:</b> type!=EMPTY => (type==expr.type && !expr.ambiguous)
 *
 * @modified Nuno Macedo // [HASLab] electrum-features
 */

public final class ExprVar extends ExprHasName implements Clause {

    /** {@inheritDoc} */
    @Override
    public void toString(StringBuilder out, int indent) {
        if (indent < 0) {
            out.append(label);
        } else {
            for (int i = 0; i < indent; i++) {
                out.append(' ');
            }
            out.append("Var ").append(label).append(" at position <").append(pos).append("> with type=").append(type).append('\n');
        }
    }

    /** Constructs an ExprVar object */
    // [HASLab] feature annotations
    private ExprVar(Pos pos, String label, Type type, Set<Integer> feats) {
        super(pos, label, type, feats); // [HASLab] feature annotations
    }

    /**
     * Constructs an ExprVar variable with the EMPTY type
     *
     * @param pos - the original position in the source file (can be null if
     *            unknown)
     * @param label - the label for this variable (it is only used for
     *            pretty-printing and does not have to be unique)
     */
    public static ExprVar make(Pos pos, String label) {
        return new ExprVar(pos, label, Type.EMPTY, new HashSet<Integer>()); // [HASLab] feature annotations
    }

    /**
     * Constructs an ExprVar variable with the given type
     *
     * @param pos - the original position in the source file (can be null if
     *            unknown)
     * @param label - the label for this variable (it is only used for
     *            pretty-printing and does not have to be unique)
     * @param type - the type
     */
    public static ExprVar make(Pos pos, String label, Type type) {
        return make(pos, label, type, new HashSet<Integer>()); // [HASLab] feature annotations
    }

    /**
     * Constructs an ExprVar variable with the given type
     *
     * @param pos - the original position in the source file (can be null if
     *            unknown)
     * @param label - the label for this variable (it is only used for
     *            pretty-printing and does not have to be unique)
     * @param type - the type
     */
    // [HASLab] feature annotations
    public static ExprVar make(Pos pos, String label, Type type, Set<Integer> feats) {
        return new ExprVar(pos, label, type, feats); // [HASLab] feature annotations
    }

    /** {@inheritDoc} */
    @Override
    public Expr resolve(Type p, Collection<ErrorWarning> warns) {
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public <T> T accept(VisitReturn<T> visitor) throws Err {
        return visitor.visit(this);
    }

    /** {@inheritDoc} */
    @Override
    public String getHTML() {
        return "<b>variable</b>: " + label + " <i>" + type + "</i>";
    }

    /** {@inheritDoc} */
    @Override
    public List< ? extends Browsable> getSubnodes() {
        return new ArrayList<Browsable>(0);
    }

    @Override
    public String explain() {
        return "var " + label + "\n" + TableView.toTable(type);
    }
}
