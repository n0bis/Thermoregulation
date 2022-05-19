package org.xtext.example.mydsl.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'\"{\"\"tracked_at\"\":'", "',\"\"value\"\":'", "'}\"'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_INT=4;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalMyDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMyDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMyDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMyDsl.g"; }


    	private MyDslGrammarAccess grammarAccess;

    	public void setGrammarAccess(MyDslGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleModel"
    // InternalMyDsl.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalMyDsl.g:54:1: ( ruleModel EOF )
            // InternalMyDsl.g:55:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMyDsl.g:62:1: ruleModel : ( ( rule__Model__DatasAssignment )* ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:66:2: ( ( ( rule__Model__DatasAssignment )* ) )
            // InternalMyDsl.g:67:2: ( ( rule__Model__DatasAssignment )* )
            {
            // InternalMyDsl.g:67:2: ( ( rule__Model__DatasAssignment )* )
            // InternalMyDsl.g:68:3: ( rule__Model__DatasAssignment )*
            {
             before(grammarAccess.getModelAccess().getDatasAssignment()); 
            // InternalMyDsl.g:69:3: ( rule__Model__DatasAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMyDsl.g:69:4: rule__Model__DatasAssignment
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Model__DatasAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getDatasAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuledata"
    // InternalMyDsl.g:78:1: entryRuledata : ruledata EOF ;
    public final void entryRuledata() throws RecognitionException {
        try {
            // InternalMyDsl.g:79:1: ( ruledata EOF )
            // InternalMyDsl.g:80:1: ruledata EOF
            {
             before(grammarAccess.getDataRule()); 
            pushFollow(FOLLOW_1);
            ruledata();

            state._fsp--;

             after(grammarAccess.getDataRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuledata"


    // $ANTLR start "ruledata"
    // InternalMyDsl.g:87:1: ruledata : ( ( rule__Data__Group__0 ) ) ;
    public final void ruledata() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:91:2: ( ( ( rule__Data__Group__0 ) ) )
            // InternalMyDsl.g:92:2: ( ( rule__Data__Group__0 ) )
            {
            // InternalMyDsl.g:92:2: ( ( rule__Data__Group__0 ) )
            // InternalMyDsl.g:93:3: ( rule__Data__Group__0 )
            {
             before(grammarAccess.getDataAccess().getGroup()); 
            // InternalMyDsl.g:94:3: ( rule__Data__Group__0 )
            // InternalMyDsl.g:94:4: rule__Data__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Data__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDataAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruledata"


    // $ANTLR start "rule__Data__Group__0"
    // InternalMyDsl.g:102:1: rule__Data__Group__0 : rule__Data__Group__0__Impl rule__Data__Group__1 ;
    public final void rule__Data__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:106:1: ( rule__Data__Group__0__Impl rule__Data__Group__1 )
            // InternalMyDsl.g:107:2: rule__Data__Group__0__Impl rule__Data__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Data__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Data__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__0"


    // $ANTLR start "rule__Data__Group__0__Impl"
    // InternalMyDsl.g:114:1: rule__Data__Group__0__Impl : ( '\"{\"\"tracked_at\"\":' ) ;
    public final void rule__Data__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:118:1: ( ( '\"{\"\"tracked_at\"\":' ) )
            // InternalMyDsl.g:119:1: ( '\"{\"\"tracked_at\"\":' )
            {
            // InternalMyDsl.g:119:1: ( '\"{\"\"tracked_at\"\":' )
            // InternalMyDsl.g:120:2: '\"{\"\"tracked_at\"\":'
            {
             before(grammarAccess.getDataAccess().getTracked_atKeyword_0()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getDataAccess().getTracked_atKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__0__Impl"


    // $ANTLR start "rule__Data__Group__1"
    // InternalMyDsl.g:129:1: rule__Data__Group__1 : rule__Data__Group__1__Impl rule__Data__Group__2 ;
    public final void rule__Data__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:133:1: ( rule__Data__Group__1__Impl rule__Data__Group__2 )
            // InternalMyDsl.g:134:2: rule__Data__Group__1__Impl rule__Data__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Data__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Data__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__1"


    // $ANTLR start "rule__Data__Group__1__Impl"
    // InternalMyDsl.g:141:1: rule__Data__Group__1__Impl : ( ( rule__Data__TrackedAssignment_1 ) ) ;
    public final void rule__Data__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:145:1: ( ( ( rule__Data__TrackedAssignment_1 ) ) )
            // InternalMyDsl.g:146:1: ( ( rule__Data__TrackedAssignment_1 ) )
            {
            // InternalMyDsl.g:146:1: ( ( rule__Data__TrackedAssignment_1 ) )
            // InternalMyDsl.g:147:2: ( rule__Data__TrackedAssignment_1 )
            {
             before(grammarAccess.getDataAccess().getTrackedAssignment_1()); 
            // InternalMyDsl.g:148:2: ( rule__Data__TrackedAssignment_1 )
            // InternalMyDsl.g:148:3: rule__Data__TrackedAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Data__TrackedAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getDataAccess().getTrackedAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__1__Impl"


    // $ANTLR start "rule__Data__Group__2"
    // InternalMyDsl.g:156:1: rule__Data__Group__2 : rule__Data__Group__2__Impl rule__Data__Group__3 ;
    public final void rule__Data__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:160:1: ( rule__Data__Group__2__Impl rule__Data__Group__3 )
            // InternalMyDsl.g:161:2: rule__Data__Group__2__Impl rule__Data__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__Data__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Data__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__2"


    // $ANTLR start "rule__Data__Group__2__Impl"
    // InternalMyDsl.g:168:1: rule__Data__Group__2__Impl : ( ',\"\"value\"\":' ) ;
    public final void rule__Data__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:172:1: ( ( ',\"\"value\"\":' ) )
            // InternalMyDsl.g:173:1: ( ',\"\"value\"\":' )
            {
            // InternalMyDsl.g:173:1: ( ',\"\"value\"\":' )
            // InternalMyDsl.g:174:2: ',\"\"value\"\":'
            {
             before(grammarAccess.getDataAccess().getValueKeyword_2()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getDataAccess().getValueKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__2__Impl"


    // $ANTLR start "rule__Data__Group__3"
    // InternalMyDsl.g:183:1: rule__Data__Group__3 : rule__Data__Group__3__Impl rule__Data__Group__4 ;
    public final void rule__Data__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:187:1: ( rule__Data__Group__3__Impl rule__Data__Group__4 )
            // InternalMyDsl.g:188:2: rule__Data__Group__3__Impl rule__Data__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Data__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Data__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__3"


    // $ANTLR start "rule__Data__Group__3__Impl"
    // InternalMyDsl.g:195:1: rule__Data__Group__3__Impl : ( ( rule__Data__ValueAssignment_3 ) ) ;
    public final void rule__Data__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:199:1: ( ( ( rule__Data__ValueAssignment_3 ) ) )
            // InternalMyDsl.g:200:1: ( ( rule__Data__ValueAssignment_3 ) )
            {
            // InternalMyDsl.g:200:1: ( ( rule__Data__ValueAssignment_3 ) )
            // InternalMyDsl.g:201:2: ( rule__Data__ValueAssignment_3 )
            {
             before(grammarAccess.getDataAccess().getValueAssignment_3()); 
            // InternalMyDsl.g:202:2: ( rule__Data__ValueAssignment_3 )
            // InternalMyDsl.g:202:3: rule__Data__ValueAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Data__ValueAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getDataAccess().getValueAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__3__Impl"


    // $ANTLR start "rule__Data__Group__4"
    // InternalMyDsl.g:210:1: rule__Data__Group__4 : rule__Data__Group__4__Impl ;
    public final void rule__Data__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:214:1: ( rule__Data__Group__4__Impl )
            // InternalMyDsl.g:215:2: rule__Data__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Data__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__4"


    // $ANTLR start "rule__Data__Group__4__Impl"
    // InternalMyDsl.g:221:1: rule__Data__Group__4__Impl : ( '}\"' ) ;
    public final void rule__Data__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:225:1: ( ( '}\"' ) )
            // InternalMyDsl.g:226:1: ( '}\"' )
            {
            // InternalMyDsl.g:226:1: ( '}\"' )
            // InternalMyDsl.g:227:2: '}\"'
            {
             before(grammarAccess.getDataAccess().getRightCurlyBracketQuotationMarkKeyword_4()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getDataAccess().getRightCurlyBracketQuotationMarkKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__Group__4__Impl"


    // $ANTLR start "rule__Model__DatasAssignment"
    // InternalMyDsl.g:237:1: rule__Model__DatasAssignment : ( ruledata ) ;
    public final void rule__Model__DatasAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:241:1: ( ( ruledata ) )
            // InternalMyDsl.g:242:2: ( ruledata )
            {
            // InternalMyDsl.g:242:2: ( ruledata )
            // InternalMyDsl.g:243:3: ruledata
            {
             before(grammarAccess.getModelAccess().getDatasDataParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruledata();

            state._fsp--;

             after(grammarAccess.getModelAccess().getDatasDataParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__DatasAssignment"


    // $ANTLR start "rule__Data__TrackedAssignment_1"
    // InternalMyDsl.g:252:1: rule__Data__TrackedAssignment_1 : ( RULE_INT ) ;
    public final void rule__Data__TrackedAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:256:1: ( ( RULE_INT ) )
            // InternalMyDsl.g:257:2: ( RULE_INT )
            {
            // InternalMyDsl.g:257:2: ( RULE_INT )
            // InternalMyDsl.g:258:3: RULE_INT
            {
             before(grammarAccess.getDataAccess().getTrackedINTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getDataAccess().getTrackedINTTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__TrackedAssignment_1"


    // $ANTLR start "rule__Data__ValueAssignment_3"
    // InternalMyDsl.g:267:1: rule__Data__ValueAssignment_3 : ( RULE_INT ) ;
    public final void rule__Data__ValueAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:271:1: ( ( RULE_INT ) )
            // InternalMyDsl.g:272:2: ( RULE_INT )
            {
            // InternalMyDsl.g:272:2: ( RULE_INT )
            // InternalMyDsl.g:273:3: RULE_INT
            {
             before(grammarAccess.getDataAccess().getValueINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getDataAccess().getValueINTTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Data__ValueAssignment_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002000L});

}